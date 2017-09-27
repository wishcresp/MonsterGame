#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netdb.h>
#include <time.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define BUFLEN 1024


int main(int argc, char *argv[])
{
	int sock, port, len;
	struct sockaddr_in serv_addr;
	char buf[BUFLEN];

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




	while (1)
	{

		len = read(sock, buf, BUFLEN-1);
		if (len < 0)
		{
			printf("Failed to read from socket :(\n");
			return EXIT_FAILURE;
		}

		len = write(sock, buf, strlen(buf));
		if (len < 0)
		{
			printf("Failed to write to socket :(\n");
			return EXIT_FAILURE;
		}

	}


	close(sock);

	return EXIT_SUCCESS;
}
