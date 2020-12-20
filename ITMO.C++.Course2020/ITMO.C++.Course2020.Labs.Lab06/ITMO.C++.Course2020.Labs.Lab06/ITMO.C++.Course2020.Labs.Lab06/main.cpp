#include <iostream>
#include <string>
#include <fstream>
using namespace std;

//--------------------------------
/*int main()
{
	system("chcp 1251");

	cout << "запись и чтение из бинарного файла" << endl; 

	double sum = 0;//суммаа чисел
	int const n = 100;//константа - размер массива
	double nums[n];//массив

	for (int i = 0; i < n; i++)//заполнение массива случайными числами
	{
		nums[i] = (rand() % 100);
	}

	cout << "Печать массива из внутренней памяти" << endl;

	for (int i = 0; i < n; i++)//печать масива
	{
		cout << nums[i]<<" ";
	}
	cout << endl;

	ofstream out("test.txt", ios::out | ios::binary);//запись в файл
	if (!out) {
		cout << "Файл открыть невозможно\n";
		return 1;
	}

	out.write((char*)nums, sizeof(nums));
	out.close();

	ifstream in("test.txt", ios::in | ios::binary);//чтение из файла
	if (!in) {
		cout << "Файл открыть невозможно";
		return 1;
	}

	in.read((char*)&nums, sizeof(nums));

	cout << "Печать массива из файла" << endl;
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
		
		cout << "Запись стиха в файл " << endl;//запись стиха в файл

		for (int i = 0; i < 3; ++i)
		{
			getline(cin, str);
			f << str << endl;
		}
		f.close();
		f.open(fileName, ios::in);

		cout << "Чтение стиха из файла" << endl;//чтение стиха из файла
		//cout << "Для прекращения записи, введите пустую строку" << endl;
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
	int min = 0; // для записи минимального значения
	int buf = 0; // для обмена значениями

	for (int i = 0; i < N; i++)
	{
		min = i; // номер текущей ячейки, как ячейки с минимальным значением
		// в цикле найдем реальный номер ячейки с минимальным значением
		for (int j = i + 1; j < N; j++)
			min = (a[j] < a[min]) ? j : min;
		// перестановка этого элемента, поменяв его местами с текущим
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
	cout << "Сортировка массива выбором и запись результа в файл" << "\n";

	fstream f;
	string fileName = "fileArray.txt";
	f.open(fileName, ios::out);
	if (f)
	{
		string str;

		//---------------
		int N;

		cout << "Число элементов в массиве=";
		cin >> N;

		int* a = new int[N];
		cout << "Обработка данных массива" << "\n";
		for (int i = 0; i < N; i++)
		{
			cout << "[" << i << "]=";
			cin >> a[i];
		}

		cout << "Массив до сортировки" << "\n";
		show_array(a, N);
		//---
		f << "Массив до сортировки" << "\n";
		for (int i = 0; i < N; i++)
			f << a[i] << " ";
		f << "\n";
		//---

		select_sort(a, N);

		cout << "Массив после сортировки" << "\n";
		
		show_array(a, N);
		//---
		f << "Массив после сортировки" << "\n";
		for (int i = 0; i < N; i++)
			f << a[i] << " ";
		f << "\n";
		//---
		//-------------------

		f.close();
		f.open(fileName, ios::in);

		cout << "Чтение информации из файла" << endl;//чтение стиха из файла
		
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