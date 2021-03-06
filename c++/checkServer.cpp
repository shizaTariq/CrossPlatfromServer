#include <sys/socket.h>
#include<sys/ioctl.h>
#include<sys/stat.h>
#include<net/if.h>
#include<netdb.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <time.h> 
#include <dirent.h>
#include <limits.h>



class initServer
{
public:
	initServer();
	void Setup();
	void FileProcess(int connfd);
	~initServer();

private:
	int port;
	int listenfd, connfd;
	struct sockaddr_in serv_addr; 

};

initServer::initServer()	//constructor
{
	this->listenfd = 0;
	this->connfd = 0;
	this->port = 55556;
	
}

initServer::~initServer()	//destructor
{
}

void initServer::Setup()	//Setup
{
 


	char sendBuff[1025];
	memset(sendBuff, '0', sizeof(sendBuff)); 
	memset(&(this->serv_addr), '0', sizeof(this->serv_addr));
	this->listenfd = socket(AF_INET, SOCK_STREAM, 0);
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	serv_addr.sin_port = htons(this->port); 

	bind(this->listenfd, (struct sockaddr*)&(this->serv_addr), sizeof(this->serv_addr)); 

	listen(listenfd, 1); 

	while(1)
	{
		printf("inititae\n");
		connfd = accept(listenfd, (struct sockaddr*)NULL, NULL); 
		printf("begin\n");
		this->FileProcess(connfd);
		
		close(connfd);
		sleep(1);
	}
}

void initServer::FileProcess(int connfd)	//Run
{

	char* fileToSend = "/home/shiza/files/testFile";
	struct stat stat_result;
	stat(fileToSend, &stat_result);
    int fileSize =  stat_result.st_size;
 


	write(connfd, (char*)&fileSize, sizeof(int));
	unsigned char* fileArray =(unsigned char*) calloc(fileSize+10,sizeof(char));
	
	FILE *fp = fopen(fileToSend,"r");
	if(fp==NULL)
    {
        printf("File opern error");
return;
	}
   		

	int nread = fread(fileArray,1,fileSize,fp);
	printf("Bytes read %d \n", nread);
		//fileArray[nread+1] ='\0';
		//printf("--->%s",fileArray);       

            // If read was success, send data. 
    if(nread > 0)
    {
        printf("Sending \n");
       write( connfd,(char*)fileArray, nread+1);
    }

            //
             // There is something tricky going on with read .. 
             // Either there was error, or we reached end of file.
             //
            if (nread < fileSize)
            {
                if (feof(fp))
                    printf("End of file\n");
                if (ferror(fp))
                    printf("Error reading\n");

            }

fclose(fp);
free(fileArray);

}

int main(int argc, char *argv[])
{
	initServer sr;
	sr.Setup();

}


