/* time.cpp */
#include <string>
#include <fstream>
#include <iostream>
#include <time.h>
#include "time.h"
#include <sstream>
#include <string>
#include <algorithm>
using namespace std;


// Конструктор Time
Time::Time()
{
	Time::set_hours(0);
	Time::set_minutes(0);
	Time::set_seconds(0);
}

// Конструктор Time
Time::Time(int totalSeconds)
{
	if (totalSeconds / (60 * 60) > 0) {
		Time::set_hours(0.5+(totalSeconds / (60 * 60)));
		totalSeconds = totalSeconds - 60 * 60 * (totalSeconds / (60 * 60));
		if (totalSeconds / (60) > 0) {
			Time::set_minutes((totalSeconds / (60)));
			totalSeconds = totalSeconds - 60 * (totalSeconds / (60));
			if (totalSeconds > 0) {
				//string str = totalSeconds;
				//str.erase(std::remove(str.begin(), str.end(), '-'), str.end());
				Time::set_seconds((int)(totalSeconds + 10));
			}
			else {
				Time::set_seconds(0);
			}
		
		}
		else {
			Time::set_minutes(0);
			if (totalSeconds > 0) {
				//string str = totalSeconds;
				//str.erase(std::remove(str.begin(), str.end(), '-'), str.end());
				Time::set_seconds((int)(totalSeconds + 10));
			}
			else {
				Time::set_seconds(0);
			}
		}
	}
	else {
		Time::set_hours(0);
		if (totalSeconds / (60) > 0) {
			Time::set_minutes((totalSeconds / (60)));
			totalSeconds = totalSeconds - 60 * (totalSeconds / (60));
		}
		else {
			Time::set_minutes(0);
			if (totalSeconds > 0) {
				//string str = totalSeconds;
				//str.erase(std::remove(str.begin(), str.end(), '-'), str.end());
				Time::set_seconds((int)(totalSeconds+10));
			}
			else {
				Time::set_seconds(0);
			}
		}
	}
}


// Конструктор Time
Time::Time(int hours, int minutes, int seconds)
{
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

// Конструктор Time
Time::Time(Time& time1, Time& time2)
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
// Конструктор Time
Time::Time(Time* time1, Time* time2)
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


// Установка количества часов в time 
void Time::set_hours(int time_hours)
{
	Time::hours = time_hours;
}
// Получение количества часов в time 
int Time::get_hours()
{
	return Time::hours;
}

// Установка количества минут в time  
void Time::set_minutes(int time_minutes)
{
	Time::minutes = time_minutes;
}
// Получение количества минут в time 
int Time::get_minutes()
{
	return Time::minutes;
}

// Установка количества секунд в time 
void Time::set_seconds(int time_seconds)
{
	Time::seconds = time_seconds;
}
// Получение количества секунд в time 
int Time::get_seconds()
{
	return Time::seconds;
}


//Сложение времени summ_time
std::string Time::summ_time(Time& time1, Time& time2)
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
	if (minutes < 10) { minutesZero = "0"; }
	if (seconds < 10) { secondsZero = "0"; }

	//put arbitrary formatted data into the stream
	ss << hoursZero << hours << ":" << minutesZero << minutes << ":" << secondsZero << seconds;

	//convert the stream buffer into a string
	std::string str = ss.str();

	return str;
}


std::string Time::get_time()
{
	std::stringstream ss;
	string hoursZero = "";
	string minutesZero = "";
	string secondsZero = "";
	if (hours < 10) { hoursZero = "0"; }
	if (minutes < 10) { minutesZero = "0"; }
	if (seconds < 10) { secondsZero = "0"; }

	//put arbitrary formatted data into the stream
	ss << hoursZero << hours << ":" << minutesZero << minutes << ":" << secondsZero << seconds;

	//convert the stream buffer into a string
	std::string str = ss.str();

	return str;
}

int Time::operator> (const Time& time2) const
{
	int result=0;
	int time1TotalSeconds= 60 * 60 * hours + 60 * minutes + seconds;
	int time2TotalSeconds = 60 * 60 * time2.hours + 60 * time2.minutes + time2.seconds;

	if (time1TotalSeconds > time2TotalSeconds) { result = 1; }
	if (time1TotalSeconds < time2TotalSeconds) { result = -1; }

	return result;
}


Time::operator int() const//возвращаем секунды
{
	int result;
	result = 60*60*hours + 60*minutes + seconds;
	return result;
}

Time Time::operator+ (const Time& time2) const
{
	int newHours = hours + time2.hours;
	int newMinutes = minutes + time2.minutes;
	int newSeconds = seconds + time2.seconds;

	if (newSeconds >= 60)
	{
		newSeconds -= 60;
		newMinutes++;
	}

	if (newMinutes >= 60)
	{
		newMinutes -= 60;
		newHours++;
	}

	return Time(newHours, newMinutes, newSeconds);
}

Time Time::operator- (const Time& time2) const
{
	int t1Hours = hours;
	int t1Minutes = minutes;
	int t1Seconds = seconds;

	int t2Hours = time2.hours;
	int t2Minutes = time2.minutes;
	int t2Seconds = time2.seconds;

	if (t1Seconds < t2Seconds)
	{
		t1Minutes -= 1;
		t1Seconds += 60;
	}

	if (t1Minutes < t2Minutes)
	{
		t1Hours -= 1;
		t1Minutes += 60;
	}

	int newHours = t1Hours - t2Hours;
	int newMinutes = t1Minutes - t2Minutes;
	int newSeconds = t1Seconds - t2Seconds;
	
	return Time(newHours, newMinutes, newSeconds);
}

std::ostream& operator<< (std::ostream& out, const Time& time1)
{
	std::stringstream ss;
	string hoursZero = "";
	string minutesZero = "";
	string secondsZero = "";
	if (time1.hours < 10) { hoursZero = "0"; }
	if (time1.minutes < 10) { minutesZero = "0"; }
	if (time1.seconds < 10) { secondsZero = "0"; }

	//put arbitrary formatted data into the stream
	ss << hoursZero << time1.hours << ":" << minutesZero << time1.minutes << ":" << secondsZero << time1.seconds;

	//convert the stream buffer into a string
	std::string str = ss.str();


	out << str << "\"\n";
	return out;
};


Time operator+(const Time& d1, int value)
{
	return Time(static_cast<int>(d1) + value);
}


Time operator+(int value, const Time& d1)
{
	return Time(static_cast<int>(d1) + value);
}
