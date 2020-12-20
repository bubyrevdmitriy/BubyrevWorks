// main.cpp
#include <iostream>
#include <vector>
using namespace std;

void sorting(int arr[], int size) {
	int j = 0;
	for (int i = 0; i < size; i++) {
		int x = arr[i];
		for (j = i - 1; j >= 0 && x < arr[j]; j--)
			arr[j + 1] = arr[j];
		arr[j + 1] = x;
	}
}

template<class T>
void sorting(T arr[], int size) {
	int j = 0;
	for (int i = 0; i < size; i++) {
		T x = arr[i];
		for (j = i - 1; j >= 0 && x < arr[j]; j--)
			arr[j + 1] = arr[j];
		arr[j + 1] = x;
	}
}

template<class T>
void printing(T arr[], int size) {
	int j = 0;
	for (int i = 0; i < size; i++) {
		
		cout << arr[i] << ";";
	}
	cout << endl;
}

template <typename T> double arrayAverage(T A[], int size)
{
	double sr = 0;
	for (int i = 0; i < size; i++)
	{
		sr += A[i];
	}
	//cout << "������� �������������� �������� �������: " << sr / size << endl;
	return sr / size;
}

/*int main()
{
	system("chcp 1251");
	cout << "������ �� ����������"<<endl;
	int arr[] = { 9,3,17,6,5,4,31,2,12 };
	int k1 = sizeof(arr) / sizeof(arr[0]);
	for (int i = 0; i < k1; i++) {
		cout << arr[i] << ";";
	}
	cout << endl;
	sorting(arr, k1);

	cout << "������ ����� ����������" << endl;
	for (int i = 0; i < k1; i++) { 
		cout << arr[i] << ";"; 
	}
	return 0;
}*/

int main()
{
	system("chcp 1251");

	int arr[] = { 9,3,17,6,5,4,31,2,12 };
	double arrd[] = { 2.1, 2.3,1.7,6.6,5.3,2.44,3.1,2.4,1.2 };
	char arrc[] = "Hello, word";
	int k1 = sizeof(arr) / sizeof(arr[0]);
	int k2 = sizeof(arrd) / sizeof(arrd[0]);
	int k3 = sizeof(arrc) / sizeof(arrc[0]) - 1;


	cout << "������ int �� ����������" << endl;
	//for (int i = 0; i < k1; i++) cout << arr[i] << ";";
	printing(arr, k1);
	cout << "������ int ����� ����������" << endl;
	sorting(arr, k1);
	//for (int i = 0; i < k1; i++) cout << arr[i] << ";";
	printing(arr, k1);
	cout<<"������� �������������� ����� � �������: " << arrayAverage(arr, k1) <<endl;



	cout << "������ double �� ����������" << endl;
	//for (int i = 0; i < k2; i++) cout << arrd[i] << ";";
	printing(arrd, k2);
	cout << "������ double ����� ����������" << endl;
	sorting(arrd, k2);
	//for (int i = 0; i < k2; i++) cout << arrd[i] << ";";
	printing(arrd, k2);
	cout << "������� �������������� ����� � �������: " << arrayAverage(arrd, k1) << endl;



	cout << "������ char �� ����������" << endl;
	//for (int i = 0; i < k3; i++) cout << arrc[i] << ";";
	printing(arrc, k3);
	cout << "������ char ����� ����������" << endl;
	sorting(arrc, k3);
	//for (int i = 0; i < k3; i++) cout << arrc[i] << ";";
	printing(arrc, k3);
	cout << "������� �������������� ����� � �������: " << arrayAverage(arrc, k1) << endl;


	return 0;
}