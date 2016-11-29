#include <stdio.h>
#include "tests/threads/tests.h"
#include "threads/init.h"
#include "threads/malloc.h"
#include "threads/synch.h"
#include "threads/thread.h"
#include "devices/timer.h"

static thread_func changing_thread;

void create_thread_priority(void) {
	msg ("Creating five theads named as A,B,C,D and E with priority 19,21,13,15,17 respectively.");
	int priority[5] = {19,21,13,15,17};
	char *name[] = {"A", "B", "C", "D", "E"};
	int i=0;
	for (i = 0; i < 5; i++) 
    {
     // printf("%s ", name[i]);
      thread_create (name[i], priority[i], changing_thread, NULL);
     // printf("%s ", name[i]);
    }
}

static void changing_thread (void *aux UNUSED) {
	msg("thread  %s is running with priority %d",thread_name(),thread_get_priority());
}
