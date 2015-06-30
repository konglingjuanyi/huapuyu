#include "ace/Timer_Queue.h"
#include "ace/Reactor.h"
#include <iostream>

#define NUMBER_TIMERS 10
static int done = 0;
static int count = 0;

class Time_Handler : public ACE_Event_Handler
{
public:
    //Method which is called back by the Reactor when timeout occurs.
    virtual int handle_timeout(const ACE_Time_Value& tv, const void* arg)
    {
		long current_count = long(arg);
		std::cout<<current_count<<std::endl;
        ACE_ASSERT(current_count == count);
        ACE_DEBUG((LM_DEBUG, "%d: Timer #%d timed out at %d!\n", count, current_count, tv.sec()));
        //Increment count
        count++;
        //Make sure assertion doesn't fail for missing 5th timer.
        if (count == 5)
            count++;
        //If all timers done then set done flag
        if (current_count == NUMBER_TIMERS - 1)
            done = 1;
        //Keep yourself registered with the Reactor.
        return 0;
    }
};

int main (int, char* [])
{
    ACE_Reactor reactor;
    Time_Handler* th = new Time_Handler;
    int timer_id[NUMBER_TIMERS];
    for (int i = 0; i < NUMBER_TIMERS; i++)
	{
        timer_id[i] = reactor.schedule_timer(th,
                                              (const void *)i, // argument sent to handle_timeout()
                                              ACE_Time_Value(2 * i + 1)); //set timer to go off with delay
	}
	
    //Cancel the fifth timer before it goes off
    reactor.cancel_timer(timer_id[5]);//Timer ID of timer to be removed
    while (!done)
	{
        reactor.handle_events();
	}

    return 0;
}