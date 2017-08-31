#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netdb.h>
#include <curses.h>
#include <time.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define BUFLEN 1024



typedef enum
{
	empty,
	pc,
	npc
} cell;


int board_size;


char cell_to_char(cell ent)
{
	switch (ent)
	{
		case pc:
			return 'X';
		case npc:
			return 'x';
		case empty:
		default:
			return '.';
	}
}


void draw_board(cell *board)
{
	int x, y;


	for (x = 0; x < board_size; x++)
		for (y = 0; y < board_size; y++)
			mvaddch(y, x, cell_to_char(board[x*y]));

}


void read_board(cell *board, char *input)
{
	int i;
	char *buf;

	i = 0;
	buf = strtok(input, ":");
	do
	{
		/*printf("Item: %s\n", buf);*/
		board[i] = atoi(buf);

	} while ((buf = strtok(NULL, ":")) != NULL);

	return;
}


int main(int argc, char *argv[])
{
	int sock, port, len, dir = 0;
	int maxx, maxy, ch;
	struct sockaddr_in serv_addr;
	char buf[BUFLEN];
	bool running = true;
	cell *board;

	if (argc != 3)
	{
		printf("Usage: %s ip port\n", argv[0]);
		return EXIT_FAILURE;
	}

	port = atoi(argv[2]);


	/* Create socket */
	sock = socket(AF_INET, SOCK_STREAM, 0);
	if (sock < 0)
	{
		printf("Failed to open socket\n");
		return EXIT_FAILURE;
	}

	/* Setup server info */
	memset(&serv_addr, 0, sizeof serv_addr);

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr(argv[1]);
	serv_addr.sin_port = htons(port);


	printf("Connecting to %s:%s\n", argv[1], argv[2]);
	/* Punch it */
	if (connect(sock, (struct sockaddr*)&serv_addr, sizeof serv_addr) < 0)
	{
		printf("Connect failed :(\n");
		return EXIT_FAILURE;
	}


	// Read board dimensions from server
	len = read(sock, buf, BUFLEN-1);
	if (len < 0)
	{
		printf("Failed to read from socket :(\n");
		return EXIT_FAILURE;
	}
	printf("Board dimensions: %s\n", buf);

	board_size = atoi(buf);

	board = malloc(sizeof (cell) * board_size * board_size);


	/* Fire up ncurses */
	initscr();
	cbreak();
	noecho();
	curs_set(0);
	keypad(stdscr, TRUE);
	srand(time(NULL));
	timeout(16);

	maxy = getmaxy(stdscr);
	maxx = getmaxx(stdscr);

	while (running)
	{

		/* Get Input (also sleep because ncurses) */
		ch = getch();
		switch (ch)
		{
			case KEY_UP:
				dir = 0;
			break;
			case KEY_DOWN:
				dir = 1;
			break;
			case KEY_LEFT:
				dir = 2;
			break;
			case KEY_RIGHT:
				dir = 3;
			break;
			case ERR:
			break;

		}

		/* Netcode and logic */
		// Send desired direction to server
		sprintf(buf, "%d", dir);
		len = write(sock, buf, strlen(buf));
		if (len < 0)
		{
			printf("Failed to write to socket :(\n");
			return EXIT_FAILURE;
		}

		// Read board layout from server
		len = read(sock, buf, BUFLEN-1);
		if (len < 0)
		{
			printf("Failed to read from socket :(\n");
			return EXIT_FAILURE;
		}

		read_board(board, buf);


		/* Draw */
		draw_board(board);

		refresh();
	}


	endwin();
	close(sock);

	return EXIT_SUCCESS;
}
