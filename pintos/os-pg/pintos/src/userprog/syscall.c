#include "userprog/syscall.h"
#include <stdio.h>
#include <syscall-nr.h>
#include "threads/interrupt.h"
#include "threads/thread.h"
#include "threads/init.h"
#include "userprog/process.h"
#include <kernel/console.h>
#include "filesys/file.h"
#include "filesys/filesys.h"
#include "devices/input.h"
#include "devices/shutdown.h"
#include "threads/vaddr.h"
#include "filesys/inode.h"
#include "filesys/directory.h"

static tid_t sys_exec(void *cmd_line, struct intr_frame *f);
#define under_phys_base(addr) if((void*)addr >= PHYS_BASE) sys_exit(-1);
#define esp_under_phys_base(f, args_num) under_phys_base(((int*)(f->esp)+args_num+1))
#define check_fd(fd, fail, f) if(fd < 0 || fd >= FD_MAX) {f->eax = fail; break;}
static void syscall_handler (struct intr_frame *);
bool sys_mkdir(char *path, struct intr_frame *f);
void
syscall_init (void) 
{
  intr_register_int (0x30, 3, INTR_ON, syscall_handler, "syscall");
}


static void
syscall_handler (struct intr_frame *f)
{
    int* i = (int*)(f->esp); // int pointer to NUMBER argument
    switch(*i){
        case SYS_WRITE:
        syscall_write(f);
        break;
        default:
        printf("default\n");
        thread_exit();
    }
    
}

void
syscall_write(struct intr_frame *f){
    uint32_t* esp = f->esp;
    int fd = *(esp + 1);
    const char* buffer = (char*)(*(esp + 2));
    int size = *(esp + 3);
    if(fd == 1){
        putbuf(buffer,size);
        f->eax = *(esp+3);
    } else
        f->eax = -1;
}

// static tid_t
// syscall_exec(struct intr_frame *f)
// {
//   int tid;
//   tid = process_execute();
//   f->eax = tid;
//   return tid;
// }


// bool sys_mkdir(char* path, struct intr_frame *f)
// {
//     bool success = filesys_create(path, 0, true);
//     f->eax = success;
//     return success;
//   }
