#include <iostream>
#include <string>
#include <fstream>
using namespace std;

//--------------------------------
/*int main()
{
	system("chcp 1251");

	cout << "������ � ������ �� ��������� �����" << endl; 

	double sum = 0;//������ �����
	int const n = 100;//��������� - ������ �������
	double nums[n];//������

	for (int i = 0; i < n; i++)//���������� ������� ���������� �������
	{
		nums[i] = (rand() % 100);
	}

	cout << "������ ������� �� ���������� ������" << endl;

	for (int i = 0; i < n; i++)//������ ������
	{
		cout << nums[i]<<" ";
	}
	cout << endl;

	ofstream out("test.txt", ios::out | ios::binary);//������ � ����
	if (!out) {
		cout << "���� ������� ����������\n";
		return 1;
	}

	out.write((char*)nums, sizeof(nums));
	out.close();

	ifstream in("test.txt", ios::in | ios::binary);//������ �� �����
	if (!in) {
		cout << "���� ������� ����������";
		return 1;
	}

	in.read((char*)&nums, sizeof(nums));

	cout << "������ ������� �� �����" << endl;
	int k = sizeof(nums) / sizeof(double);
	for (int i = 0; i < k; i++)
	{
		sum = sum + nums[i];
		cout << nums[i] << ' ';
	}
	cout << "\nsum = " << sum << endl;

	in.close();

	return 0;
}*/
//------------------------------------------------------------------------------------------------------------------------
//1
/*int main()
{
	system("chcp 1251");
	fstream f;
	string fileName = "filePoem.txt";
	f.open(fileName, ios::out);
	if (f)
	{
		string str;
		
		cout << "������ ����� � ���� " << endl;//������ ����� � ����

		for (int i = 0; i < 3; ++i)
		{
			getline(cin, str);
			f << str << endl;
		}
		f.close();
		f.open(fileName, ios::in);

		cout << "������ ����� �� �����" << endl;//������ ����� �� �����
		//cout << "��� ����������� ������, ������� ������ ������" << endl;
		if (f)
		{
			while (getline(f, str))
				cout << str << endl;

			f.close();
		}
		else
			cout << "Error opening the file for reading!" << endl;
	}
	else
		cout << "Error opening file for writing!" << endl;

	cin.get();
	return 0;
}*/

//------------------------------------------------------------------------------------------------------------------------
//2

void show_array(const int Arr[], const int N)
{
	for (int i = 0; i < N; i++)
		cout << Arr[i] << " ";
	cout << "\n";
}

void select_sort(int a[], const int N)
{
	int min = 0; // ��� ������ ������������ ��������
	int buf = 0; // ��� ������ ����������

	for (int i = 0; i < N; i++)
	{
		min = i; // ����� ������� ������, ��� ������ � ����������� ���������
		// � ����� ������ �������� ����� ������ � ����������� ���������
		for (int j = i + 1; j < N; j++)
			min = (a[j] < a[min]) ? j : min;
		// ������������ ����� ��������, ������� ��� ������� � �������
		if (i != min)
		{
			buf = a[i];
			a[i] = a[min];
			a[min] = buf;
		}
	}
}

int main()
{
	system("chcp 1251");
	cout << "���������� ������� ������� � ������ �������� � ����" << "\n";

	fstream f;
	string fileName = "fileArray.txt";
	f.open(fileName, ios::out);
	if (f)
	{
		string str;

		//---------------
		int N;

		cout << "����� ��������� � �������=";
		cin >> N;

		int* a = new int[N];
		cout << "��������� ������ �������" << "\n";
		for (int i = 0; i < N; i++)
		{
			cout << "[" << i << "]=";
			cin >> a[i];
		}

		cout << "������ �� ����������" << "\n";
		show_array(a, N);
		//---
		f << "������ �� ����������" << "\n";
		for (int i = 0; i < N; i++)
			f << a[i] << " ";
		f << "\n";
		//---

		select_sort(a, N);

		cout << "������ ����� ����������" << "\n";
		
		show_array(a, N);
		//---
		f << "������ ����� ����������" << "\n";
		for (int i = 0; i < N; i++)
			f << a[i] << " ";
		f << "\n";
		//---
		//-------------------

		f.close();
		f.open(fileName, ios::in);

		cout << "������ ���������� �� �����" << endl;//������ ����� �� �����
		
		if (f)
		{
			while (getline(f, str))
				cout << str << endl;

			f.close();
		}
		else
			cout << "Error opening the file for reading!" << endl;
	}
	else
		cout << "Error opening file for writing!" << endl;

	cin.get();
	return 0;	
}
//------------------------------------------------------------------------------------------------------------------------