#include <iostream>
#include <string>
#include <sstream>
using namespace std;
class Time
{
public:

	class ExScore //����� ����������
	{
	public:
		string origin; //��� ����� �������
		int iValue; //��� �������� ���������� ��������

		ExScore(string originEnter, int iValueEnter)
		{
			origin = originEnter; //������ � ������ ��������� ������
			iValue = iValueEnter; //����������� ������������ ��������
		}
	};

	// ����������� Time
	Time(int hours, int minutes, int seconds)
	{
		if (hours < 0) {
			throw ExScore("� ������������ Time(int, int, int)", hours);
		}
		if (minutes < 0) {
			throw ExScore("� ������������ Time(int, int, int)", minutes);
		}
		if (seconds < 0) {
			throw ExScore("� ������������ Time(int, int, int)", seconds);
		}


		if (seconds >= 60)
		{
			seconds -= 60;
			minutes++;
		}

		if (minutes >= 60)
		{
			minutes -= 60;
			hours++;
		}

		Time::set_hours(hours);
		Time::set_minutes(minutes);
		Time::set_seconds(seconds);
	}

	// ����������� Time
	Time(Time& time1, Time& time2)
	{
		int hours = time1.get_hours() + time2.get_hours();
		int minutes = time1.get_minutes() + time2.get_minutes();
		int seconds = time1.get_seconds() + time2.get_seconds();

		if (seconds >= 60)
		{
			seconds -= 60;
			minutes++;
		}

		if (minutes >= 60)
		{
			minutes -= 60;
			hours++;
		}

		Time::set_hours(hours);
		Time::set_minutes(minutes);
		Time::set_seconds(seconds);
	}
	// ����������� Time
	Time(Time* time1, Time* time2)
	{
		int hours = time1->get_hours() + time2->get_hours();
		int minutes = time1->get_minutes() + time2->get_minutes();
		int seconds = time1->get_seconds() + time2->get_seconds();

		if (seconds >= 60)
		{
			seconds -= 60;
			minutes++;
		}

		if (minutes >= 60)
		{
			minutes -= 60;
			hours++;
		}

		Time::set_hours(hours);
		Time::set_minutes(minutes);
		Time::set_seconds(seconds);
	}


	// ��������� ���������� ����� � time 
	void set_hours(int time_hours)
	{
		Time::hours = time_hours;
	}
	// ��������� ���������� ����� � time 
	int get_hours()
	{
		return Time::hours;
	}

	// ��������� ���������� ����� � time  
	void set_minutes(int time_minutes)
	{
		Time::minutes = time_minutes;
	}
	// ��������� ���������� ����� � time 
	int get_minutes()
	{
		return Time::minutes;
	}

	// ��������� ���������� ������ � time 
	void set_seconds(int time_seconds)
	{
		seconds = time_seconds;
	}
	// ��������� ���������� ������ � time 
	int get_seconds()
	{
		return seconds;
	}


	//�������� ������� summ_time
	string summ_time(Time& time1, Time& time2)
	{
		int hours = time1.get_hours() + time2.get_hours();
		int minutes = time1.get_minutes() + time2.get_minutes();
		int seconds = time1.get_seconds() + time2.get_seconds();

		if (seconds >= 60)
		{
			seconds -= 60;
			minutes++;
		}

		if (minutes >= 60)
		{
			minutes -= 60;
			hours++;
		}

		std::stringstream ss;
		string hoursZero = "";
		string minutesZero = "";
		string secondsZero = "";
		if (hours < 10) { hoursZero = "0"; }
		if (hours < 10) { minutesZero = "0"; }
		if (hours < 10) { secondsZero = "0"; }

		//put arbitrary formatted data into the stream
		ss << hoursZero << hours << ":" << minutesZero << minutes << ":" << secondsZero << seconds;

		//convert the stream buffer into a string
		std::string str = ss.str();

		return str;
	}


	string get_time()
	{
		std::stringstream ss;
		string hoursZero = "";
		string minutesZero = "";
		string secondsZero = "";
		if (hours < 10) { hoursZero = "0"; }
		if (hours < 10) { minutesZero = "0"; }
		if (hours < 10) { secondsZero = "0"; }

		//put arbitrary formatted data into the stream
		ss << hoursZero << hours << ":" << minutesZero << minutes << ":" << secondsZero << seconds;

		//convert the stream buffer into a string
		std::string str = ss.str();

		return str;
	}	

private:
	// ����
	int hours;
	// ������
	int minutes;
	// �������
	int seconds;

};


int main()
{
	system("chcp 1251");
	// �������� ������� ������ Student
	int hours;
	int minutes;
	int seconds;

	cout << "������� ����� Time(���������� ���������� ��������� ���� �������������� �������): " << endl;

	cout << "Hours: ";
	cin >> hours;

	cout << "Minutes: ";
	cin >> minutes;

	cout << "Seconds: ";
	cin >> seconds;

	try
	{
		// ���������� ������������� ������ � ������ ������ Student
		Time* time01 = new Time(hours, minutes, seconds);

		cout << time01->get_time() << endl;		
	}
	catch (Time::ExScore& ex)
	{
		cout << "\n������ ������������� " << ex.origin;
		cout << "\n��������� �������� ������ " << ex.iValue << " �������� ������������\n";
	}


	return 0;
}