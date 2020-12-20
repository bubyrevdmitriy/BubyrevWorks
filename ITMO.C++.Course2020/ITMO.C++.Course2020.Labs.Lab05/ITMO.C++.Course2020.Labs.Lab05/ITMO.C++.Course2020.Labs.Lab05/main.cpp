#include <iostream>
#include <string>
#include <time.h>
#include <vector>
using namespace std;
//--------------------------------------------
/*int main()
{
	system("chcp 1251");
	int n;

	cout << "Число элементов в массиве=";
	cin >> n;

	int* mas = new int[n];
	cout << "Обработка данных массива" << "\n";
	for (int i = 0; i < n; i++)
	{
		cout << "mas[" << i << "]=";
		cin >> mas[i];
	}

	int s = 0;//Сумма элементов масива
	for (int i = 0; i < n; i++)
	{
		s += mas[i];
	}

	double sMidArif = s/n;//Среднее арифметическое по масиву

	int sPlus = 0;//Сумма положительных элементов масива
	for (int i = 0; i < n; i++)
	{
		if (mas[i] > 0) { sPlus += mas[i]; }
	}

	int sMinus = 0;//Сумма отрицательных элементов масива
	for (int i = 0; i < n; i++)
	{
		if (mas[i]<0) { sMinus += mas[i]; }
	}

	int sChet = 0;//Сумма четных элементов масива
	for (int i = 0; i < n; i++)
	{
		if (i%2== 0) { sChet += mas[i]; }
	}

	int sNeChet = 0;//Сумма нечетных элементов масива
	for (int i = 0; i < n; i++)
	{
		if (i % 2 != 0) { sNeChet += mas[i]; }
	}

	int elementMin = mas[0];//минимальный элемент массива
	int elementMinIndex = 0;//индекс минимального элемента миссива
	for (int i = 1; i < n; i++)
	{
		if (mas[i] < elementMin) {
			elementMin = mas[i];
			elementMinIndex = i;
		}
	}

	int elementMax = mas[0];//максимальный элемент массива
	int elementMaxIndex = 0;//индекс максимального элемента в массиве
	for (int i = 1; i < n; i++)
	{
		if (mas[i] > elementMax) {
			elementMax = mas[i];
			elementMaxIndex = i;
		}
	}


	cout << "Сумма элементов масива: " << s << "\n";
	cout << "Среднее арифметическое по масиву: " << sMidArif << "\n";
	cout << "Сумма положительных элементов масива: " << sPlus << "\n";
	cout << "Сумма отрицательных элементов масива: " << sMinus << "\n";
	cout << "Сумма четных элементов масива: " << sChet << "\n";
	cout << "Сумма нечетных элементов масива: " << sNeChet << "\n";
	cout << "минимальный элемент массива: " << elementMin <<" . С индексом: " <<elementMinIndex <<"\n";
	cout << "максимальный элемент массива: " << elementMax <<" . С индексом: " << elementMaxIndex <<"\n";
	return 0;
}*/
//-----------------------------------------------------
/*int main()
{
	system("chcp 1251");
	cout << "Сортировка массива выбором" << "\n";

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

	//const int N = 10;
	//int a[N] = { 1, 25, 6, 32, 43, 5, 96, 23, 4, 55 };
	
	int min = 0; // для записи минимального значения
	int buf = 0; // для обмена значениями

	cout << "Массив до сортировки" << "\n";
	//for (int i : a)	{cout << i << '\t';	}
	for (int i = 0; i < N; i++) { cout << a[i] << '\t'; }
	cout <<  "\n";


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

	cout << "Массив после сортировки" << "\n";
	//for (int i : a) { cout << i << '\t'; }
	for (int i = 0; i < N; i++) { cout << a[i] << '\t'; }
	return 0;
}*/

//---------------------------------------------------------

/*void show_array(const int Arr[], const int N)
{
	for (int i = 0; i < N; i++)
		cout << Arr[i] << " ";
	cout << "\n";
}

bool from_min(const int a, const int b)
{
	return a > b;
}
bool from_max(const int a, const int b)
{
	return a < b;
}

void bubble_sort(int Arr[], const int N, bool (*compare)(int a, int b))
{
	for (int i = 1; i < N; i++)
	{
		for (int j = 0; j < N - 1; j++)
		{
			if ((*compare)(Arr[j], Arr[j + 1])) swap(Arr[j], Arr[j + 1]);
		}
	}
}



int main()
{
	system("chcp 1251");
	bool (*from_f[2])(int, int) = { from_min,from_max };

	int N;

	cout << "Число элементов в массиве=";
	cin >> N;

	int* A = new int[N];
	cout << "Обработка данных массива" << "\n";
	for (int i = 0; i < N; i++)
	{
		cout << "[" << i << "]=";
		cin >> A[i];
	}
	//const int N = 10;
	int my_choose = 0;
	cout << "Использование указателя на функцию" << "\n";
	//int A[N] = { 9,8,7,6,1,2,3,5,4,9 };
	
	cout << "Исходные данные: ";
	show_array(A, N);

	cout << "1. Сортировать по возрастанию\n";
	cout << "2. Сортировать по убыванию\n";
	cin >> my_choose;

	
	//switch (my_choose)
	//{
	//case 1: bubble_sort(A, N, from_min); break;
	//case 2: bubble_sort(A, N, from_max); break;
	//default: cout << "\rНеизвестная операция ";
	//}
	bubble_sort(A, N, (*from_f[my_choose - 1]));

	cout << "Массив после сортировки: ";
	show_array(A, N);

	return 0;
}*/
//---------------------------------------------------
/*int main() {

	system("chcp 1251");
	cout << "Сохранение результатов в масиве \n";

	srand(time(NULL));
	int a, b, c;
	a = 2;
	b = 2;
	int k = 0;
	const int n = 10;
	int mas[n];
	vector<int> v1;
	vector<int> v2;

	for (int i = 0; i < n; i++) {
		cout <<"Сколько будет:"<<a<<"х" <<b<<"? Ваш ответ: \n";
		cin >> c; 
		mas[i] = c;

		if (a * b != c) {
			v2.push_back(c); k++;
		} else { 
			v1.push_back(c); 
		}
	}

	cout << "\nAll: "; 
	for (int i = 0; i < n; i++) 
	{ 
		cout << mas[i] << ends; 
	}

	cout << "\nGood: "; 
	for (int i = 0; i < v1.size(); i++) 
	{ cout << v1[i] << ends; } 
	cout << "\nBad: "; 
	for (int i = 0; i < v2.size(); i++) 
	{ cout << v2[i] << ends; }

	return 0;
}*/
//--------------------------------------------------
//1.1
/*int summ_array(const int mas[], const int n) {
	int s = 0;//Сумма элементов масива
	for (int i = 0; i < n; i++)
	{
		s += mas[i];
	}
	return s;
}


int sPlus_array(const int mas[], const int n) {
	int sPlus = 0;//Сумма положительных элементов масива
	for (int i = 0; i < n; i++)
	{
		if (mas[i] > 0) { sPlus += mas[i]; }
	}
	return sPlus;
}

int sMinus_array(const int mas[], const int n) {
	int sMinus = 0;//Сумма отрицательных элементов масива
	for (int i = 0; i < n; i++)
	{
		if (mas[i] < 0) { sMinus += mas[i]; }
	}
	return sMinus;
}
//---------
int sChet_array(const int mas[], const int n) {
	int sChet = 0;//Сумма четных элементов масива
	for (int i = 0; i < n; i++)
	{
		if (i % 2 == 0) { sChet += mas[i]; }
	}
	return sChet;
}

int sNeChet_array(const int mas[], const int n) {
	int sNeChet = 0;//Сумма нечетных элементов масива
	for (int i = 0; i < n; i++)
	{
		if (i % 2 != 0) { sNeChet += mas[i]; }
	}
	return sNeChet;
}
//--------------
void elementMin_array(const int, const int,int&, int&);

void elementMin_array(const int mas[], const int n, int& elementMin, int& elementMinIndex) {
	for (int i = 1; i < n; i++)
	{
		if (mas[i] < elementMin) {
			elementMin = mas[i];
			elementMinIndex = i;
		}
	}	
}
//---------------------
void elementMax_array(const int, const int, int&, int&);

void elementMax_array(const int mas[], const int n, int& elementMax, int& elementMaxIndex) {
	for (int i = 1; i < n; i++)
	{
		if (mas[i] > elementMax) {
			elementMax = mas[i];
			elementMaxIndex = i;
		}
	}
}

int main()
{
	system("chcp 1251");
	int n;

	cout << "Число элементов в массиве=";
	cin >> n;

	int* mas = new int[n];
	cout << "Обработка данных массива" << "\n";
	for (int i = 0; i < n; i++)
	{
		cout << "mas[" << i << "]=";
		cin >> mas[i];
	}

	int s = summ_array(mas, n);//Сумма элементов масива
	
	double sMidArif = s / n;//Среднее арифметическое по масиву

	int sPlus = sPlus_array(mas, n);//Сумма положительных элементов масива
	
	int sMinus = sMinus_array(mas, n);//Сумма отрицательных элементов масива
	
	int sChet = sChet_array(mas, n);//Сумма четных элементов масива
	
	int sNeChet = sNeChet_array(mas, n);//Сумма нечетных элементов масива
	
	int elementMin = mas[0];//минимальный элемент массива
	int elementMinIndex = 0;//индекс минимального элемента миссива
	elementMin_array(mas, n, elementMin,  elementMinIndex);
	

	int elementMax = mas[0];//максимальный элемент массива
	int elementMaxIndex = 0;//индекс максимального элемента в массиве
	elementMax_array(mas, n, elementMax, elementMaxIndex);

	cout << "Сумма элементов масива: " << s << "\n";
	cout << "Среднее арифметическое по масиву: " << sMidArif << "\n";
	cout << "Сумма положительных элементов масива: " << sPlus << "\n";
	cout << "Сумма отрицательных элементов масива: " << sMinus << "\n";
	cout << "Сумма четных элементов масива: " << sChet << "\n";
	cout << "Сумма нечетных элементов масива: " << sNeChet << "\n";
	cout << "минимальный элемент массива: " << elementMin << " . С индексом: " << elementMinIndex << "\n";
	cout << "максимальный элемент массива: " << elementMax << " . С индексом: " << elementMaxIndex << "\n";
	return 0;
}*/

//--------------------------------------------------
//1.2

/*void show_array(const int Arr[], const int N)
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
	cout << "Сортировка массива выбором" << "\n";

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
	show_array(a,  N);
	
	select_sort(a, N);
	
	cout << "Массив после сортировки" << "\n";
	show_array(a, N);
	return 0;
}*/

//--------------------------------------------------
//2
int* max_vect(int kc,int a[], int b[])
{
	int* cArray = new int[kc];

	for (int i = 0; i < kc; i++)
	{
		if (a[i]> b[i]) {
			cArray[i]= a[i];
		} else {
			cArray[i] = b[i]; 
		}
	}

	return cArray;
}

int main()
{
	system("chcp 1251");
	int a[] = { 1,2,3,4,5,6,7,2 };
	int b[] = { 7,6,5,4,3,2,1,3 };
	int kc = sizeof(a) / sizeof(a[0]); //Количество элементов массива
	int* c; //Указатель на результирующий массив
	c = max_vect(kc, a, b); //вызов функции для создания массива
	for (int i = 0; i < kc; i++) //Вывод результата.
		cout << c[i] << " ";
	cout << endl;
	delete[]c; //Возврат памяти.
	return 0;
}

//--------------------------------------------------