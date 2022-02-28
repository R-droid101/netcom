#include <iostream>

using namespace std;

int check;
void receiver(int, int);

void sender(int n){
	cout<<"\nSending Frame: "<<n;
	receiver(rand()%2, n);
}

void receiver(int ack, int n){
	cout<<"\t";
	if (ack == 0){ //timeout (Frame not received)
		cout<<"--- Timeout";
		sender(n);
	}else{
		cout<<"Received Frame: "<<n;
		if(n == check){
			cout<<" Duplicate, Discard Acknowledgement:"<<n;
			return;
		}
		check++;
		if(rand()%2){ //Acknowledge
			cout<<" Acknowledgement: "<<n;
			return;
		}
		cout<<" Timeout";
		sender(n);
	}
}

int main(){
	int n;
	cout<<"Enter the number of frames: ";
	cin>>n;
	cout<< "Sender has to send frames: ";
	for (int i=1;i<=n;i++)
		cout<<i<<" ";
	cout<<"\nSender\t\tReceiver\n";

	check = 0;
	for(int i=1;i<=n;i++){
		sender(i);
	}
	cout<<"\n\n";
	return 0;
}