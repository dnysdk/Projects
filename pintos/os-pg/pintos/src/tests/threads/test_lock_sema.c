#include <stdio.h>
#include "tests/threads/tests.h"
#include "threads/thread.h"
#include "threads/init.h"
#include "threads/malloc.h"
#include "threads/synch.h"
#include "devices/timer.h"

static struct semaphore sema;
static struct lock lock;
static thread_func sema_thread_A;
static thread_func sema_thread_B;
static thread_func lock_thread_C;
static thread_func lock_thread_D;

void
test_lock_sema(void){

	lock_init (&lock);
	sema_init (&sema, 0);

    thread_create ("A", PRI_DEFAULT, sema_thread_A, NULL);
    thread_create ("B", PRI_DEFAULT, sema_thread_B, NULL);
    thread_create ("C", PRI_DEFAULT, lock_thread_C, NULL);
    thread_create ("D", PRI_DEFAULT, lock_thread_D, NULL);

}

static void
sema_thread_A(void *aux UNUSED){
	sema_up(&sema);
	msg("Will print because sema_up took place, sema = 1 now!, A");

}

static void
sema_thread_B(void *aux UNUSED){
		
		sema_down(&sema);
		msg("printing, sema_down took place sema = 0(wait for 2), B");
		timer_sleep (2 * TIMER_FREQ);
		sema_down(&sema);
		msg("This will never print!, B");
}

static void
lock_thread_C(void *aux UNUSED){
	  msg("Acquiring lock... by C");
	  lock_acquire (&lock);
	  msg("lock acquired by C, now wait for 5 sec then released.");
	  timer_sleep (5 * TIMER_FREQ);
	  lock_release (&lock);
	  msg("lock released, C.");

}

static void
lock_thread_D(void *aux UNUSED){
	msg("Will be able to aquire lock only after 5 sec when C releases, D");
	lock_acquire (&lock);
		msg("Lock acquired by D!");
	lock_release(&lock);
}