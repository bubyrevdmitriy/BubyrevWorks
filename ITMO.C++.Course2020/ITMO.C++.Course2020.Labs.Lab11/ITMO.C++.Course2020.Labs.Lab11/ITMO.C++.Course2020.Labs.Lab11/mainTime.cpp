/*#include <iostream>
#include <string>
#include "time.h"
using namespace std; 

int main()
{
	system("chcp 1251");
	int hours;
	int minutes;
	int seconds;

	

	cout << "Создаем класс №1: " << endl;

	cout << "Hours: ";
	cin >> hours;

	cout << "Minutes: ";
	cin >> minutes;

	cout << "Seconds: ";
	cin >> seconds;

	Time time01 =  Time(hours, minutes, seconds);
	cout <<"time01="<< time01<< endl;


	cout << "Создаем класс №2: ";

	cout << "Hours: ";
	cin >> hours;

	cout << "Minutes: ";
	cin >> minutes;

	cout << "Seconds: ";
	cin >> seconds;

	Time time02 = Time(hours, minutes, seconds);
	cout <<"time02= "<< time02 << endl;


	cout << "Создаем новый объект класса time на основе двух старых оъектов: " << endl;
	Time time03 = time01 + time02;
	cout << "time03 = time01 + time02 = "<< time03<< endl;

	Time time04 = time03 - time02;
	cout << "time04 = time03 - time02 = " << time04 << endl;

	int time04int = (int)time04;
	cout << "time04 (int) = " << time04int << endl;

	Time time05 = time03 + 320;
	cout << "time05 = time03 + 320 = " << time05 << endl;

	Time time06 = 320 + time03;
	cout << "time06 = 320 + time03 = " << time06 << endl;

	int compResult = time01 > time02;
	cout << "time01 > time02 " << compResult << endl;
	cout << "1 if true, -1 if false, 0 if equals "  << endl;

	return 0;
}*/
