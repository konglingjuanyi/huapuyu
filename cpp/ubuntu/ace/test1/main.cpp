#include <iostream>
using namespace std;

#include "ace/INET_Addr.h"
#include "ace/SOCK_Connector.h"
#include "ace/SOCK_Stream.h"

int main(int argc, char** argv)
{
    const char* pathname = argc > 1 ? argv[1] : "index.html";
    const char* server_hostname = argc > 2 ? argv[2] : "ace.ece.uci.edu";

    ACE_SOCK_Connector connector;
    ACE_SOCK_Stream peer;
    ACE_INET_Addr peer_addr;

    if (peer_addr.set(80, server_hostname) == -1)
    {
        return 1;
    }
    else if (connector.connect(peer, peer_addr) == -1)
    {
        return 1;
    }
    
    cout<<"success"<<endl;

    return (EXIT_SUCCESS);
}


