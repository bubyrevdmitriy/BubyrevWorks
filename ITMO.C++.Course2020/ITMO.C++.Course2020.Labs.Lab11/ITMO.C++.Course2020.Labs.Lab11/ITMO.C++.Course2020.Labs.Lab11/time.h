/* time.h */
#pragma once /* ������ �� �������� ����������� ������������� ����� */
#include <string>
//#include <int>
using namespace std;
class Time
{
public:

	// ��������� ���������� �����
	void set_hours(int);
	// ��������� ���������� �����
	int get_hours();

	// ��������� ���������� �����
	void set_minutes(int);
	// ��������� ���������� �����
	int get_minutes();

	// ��������� ���������� ������
	void set_seconds(int);
	// ��������� ���������� ������
	int get_seconds();

	// ��������� �������
	string get_time();

	// �������� �������
	string summ_time(Time&, Time&);
	string summ_time(Time* time1, Time*);
	//string summ_time(Time, Time);

	// ����������� ������ Time �1
	Time();

	// ����������� ������ Time �2
	Time(int, int, int);
	//����������� �� ������
	Time(int);



	// ����������� ������ Time �3
	Time(Time&, Time&);
	Time(Time* time1, Time* time2);
	//Time(Time*, *Time*);

	// ������ ������ � ������ � ����
	//void save();
	// ���������� ������ time
	//~Time();



	operator int() const;

	Time operator+ (const Time&) const;
	Time operator- (const Time&) const;
	int  operator> (const Time&) const;
	friend std::ostream& operator<< (std::ostream& out, const Time& dist);

	friend Time operator+(const Time& d1, int value);
	friend Time operator+(int value, const Time& d1);
	

private:
	// ����
	int hours;
	// ������
	int minutes;
	// �������
	int seconds;
};
