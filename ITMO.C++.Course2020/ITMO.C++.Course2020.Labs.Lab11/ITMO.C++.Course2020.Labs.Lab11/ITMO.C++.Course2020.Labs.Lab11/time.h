/* time.h */
#pragma once /* Защита от двойного подключения заголовочного файла */
#include <string>
//#include <int>
using namespace std;
class Time
{
public:

	// Установка количества часов
	void set_hours(int);
	// Получение количества часов
	int get_hours();

	// Установка количества минут
	void set_minutes(int);
	// Получение количества минут
	int get_minutes();

	// Установка количества секунд
	void set_seconds(int);
	// Получение количества секунд
	int get_seconds();

	// Получение времени
	string get_time();

	// Сложение времени
	string summ_time(Time&, Time&);
	string summ_time(Time* time1, Time*);
	//string summ_time(Time, Time);

	// Конструктор класса Time №1
	Time();

	// Конструктор класса Time №2
	Time(int, int, int);
	//конструктор из секунд
	Time(int);



	// Конструктор класса Time №3
	Time(Time&, Time&);
	Time(Time* time1, Time* time2);
	//Time(Time*, *Time*);

	// Запись данных о классе в файл
	//void save();
	// Деструктор класса time
	//~Time();



	operator int() const;

	Time operator+ (const Time&) const;
	Time operator- (const Time&) const;
	int  operator> (const Time&) const;
	friend std::ostream& operator<< (std::ostream& out, const Time& dist);

	friend Time operator+(const Time& d1, int value);
	friend Time operator+(int value, const Time& d1);
	

private:
	// Часы
	int hours;
	// Минуты
	int minutes;
	// Секунды
	int seconds;
};
