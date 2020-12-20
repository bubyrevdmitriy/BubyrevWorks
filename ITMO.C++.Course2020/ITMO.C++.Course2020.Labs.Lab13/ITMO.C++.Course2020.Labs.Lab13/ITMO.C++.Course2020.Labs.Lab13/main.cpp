#include <iostream>
#include <string>
#include <windows.h>
using namespace std;
class Item
{
private:
	string title;
	double price;
public:
	virtual void getdata()
	{
		cout << "\nВведите заголовок : ";
		cin >> title;
		cout << "Введите цену : ";
		cin >> price;
	}
	virtual void putdata()
	{
		cout << "\nЗаголовок: " << title;
		cout << "\nЦена: " << price;
	}
};

class Paperbook : public Item
{
private:
	int pages;
public:
	void getdata()
	{
		Item::getdata();
		cout << "Введите число страниц : ";
		cin >> pages;
	}
	void putdata()
	{
		Item::putdata();
		cout << "\nСтраниц: " << pages;
	}
};

class AudioBook : public Item
{
private:
	double time;
public:
	void getdata()
	{
		Item::getdata();
		cout << "Введите время звучания : ";
		cin >> time;
	}
	void putdata()
	{
		Item::putdata();
		cout << "\nВремя звучания : " << time;
	}
};

int main()
{
	system("chcp 1251");
	SetConsoleOutputCP(1251);
	Item* pubarr[100];//массив указателей на класс Item
	int n = 0;
	char choice;
	do
	{
		cout << "\nВводить данные для книги или звукового файла(b / a) ? ";
		cin >> choice;
		if (choice == 'b')
			pubarr[n] = new Paperbook;
		else
			pubarr[n] = new AudioBook;
		pubarr[n++]->getdata();
		cout << "Продолжать((у / n) ? ";
		cin >> choice;
	} while (choice == 'y');

	for (int j = 0; j < n; j++) //цикл по всем объектам
		pubarr[j]->putdata(); //вывести данные о публикации
	cout << endl;
	return 0;
}