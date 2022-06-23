/*************************************************************************
	> File Name: test.c
	> Author: Congenital
	> Mail: Congenital_andy@163.com 
	> Created Time: 四  6/23 10:40:02 2022
 ************************************************************************/

#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<errno.h>

int main() {
	const char* file_name = "/data/local/tmp/io_base_file";
	if (access(file_name, F_OK) != 0) {
		printf("not exist %s\n", file_name);
		int ok = creat(file_name, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP | S_IROTH | S_IWOTH);
		printf("create file %s %d %s\n", file_name, ok, strerror(errno));
	}

	int fd = open(file_name, O_RDWR);
	if (!fd) {
		printf("can't open file %s\n", file_name);
	}

	char w_data[] = { "test" };
	int size = write(fd, w_data, sizeof(w_data));
	printf("write %s to write size %d\n", w_data, size);

	lseek(fd, SEEK_SET, 0);
	char r_data[5];
	size = read(fd, r_data, sizeof(w_data));
	printf("read %s to read size %d\n", r_data, size);

	close(fd);
	return 0;
}
