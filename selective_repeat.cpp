#include <iostream>

using namespace std;

int win, cnt, sendPoint;
int sending[100];

void receiver(int, int);

void sender(int n){
    cout<<"\nSending Frame: "<<n;
    sending[cnt++] = n;
}

void receiver(){
    if(rand()%3 == 0){ //Failed
        cout<<"\nFailed Acknowledgement : "<<sending[sendPoint];
        sender(sending[sendPoint++]);
    }else{
        cout<<"\n\nReceived Frame: "<<sending[sendPoint++];
    }
}

int main(){
    int n;
    cnt = 0, sendPoint = 0;
    cout<<"Enter window size : ";
    cin>>win;
    cout<<"Number of frames : ";
    cin>>n;
    cout<< "Sender has to send frames: ";
    for (int i=1;i<=n;i++)
        cout<<i<<" ";

    cout<<"\n\n";
    int i = 1;
    while(true){ 
        if(i <= n){
            sender(i++);
        }
        if(i > win)
            receiver();

        if(cnt == sendPoint)
            break;
    }
    cout<<"\n\n";
    return 0;
}
