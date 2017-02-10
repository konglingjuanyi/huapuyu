#include <signal.h>
#include "ace/Reactor.h"
#include "ace/Event_Handler.h"

class MyEventHandler: public ACE_Event_Handler
{
    int handle_signal(int signum, siginfo_t*, ucontext_t*)
    {
        switch (signum)
        {
        case SIGILL:
            ACE_DEBUG((LM_DEBUG, "You pressed SIGILL \n"));
            break;
        case SIGINT:
            ACE_DEBUG((LM_DEBUG, "You pressed SIGINT \n"));
            break;
        }
        //ע�⣺�������0�¼��������������ڷ�Ӧ���ϵĵǼǣ�
        //�������<0��Ӧ�����Զ��ص����¼���������handle_close()���������������Լ����ڲ����ɱ��в��
        return -1;
    }

    virtual int handle_close(ACE_HANDLE handle, ACE_Reactor_Mask mask)
    {
        ACE_DEBUG((LM_DEBUG, "Handle close \n"));
        return 0;
    }
};

//��������DOS������ִ�У�ִ��CTRL+C�鿴���н��
int main(int argc, char* argv[])
{
    MyEventHandler* eh = new MyEventHandler;

    ACE_Reactor::instance()->register_handler(SIGILL, eh);
    ACE_Reactor::instance()->register_handler(SIGINT, eh);

    while (1)
    {
        ACE_Reactor::instance()->handle_events();
    }

	return 0;
}