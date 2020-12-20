#include <iostream>
#include <windows.h>
using namespace std;
/*struct Distance
{
	int feet;
	double inches;

	void ShowDist()
	{
		cout << feet << "\'-" << inches << "\"\n";
	}

};
Distance AddDist(const Distance& d1, const Distance& d2)
{
	Distance d;
	d.feet = d1.feet + d2.feet;
	d.inches = d1.inches + d2.inches;
	if (d.inches >= 12.0)
	{
		d.inches -= 12.0;
		d.feet++;
	}
	return d;
}
Distance InputDist()
{
	Distance d;
	cout << "\nВведите число футов: ";
	cin >> d.feet;
	cout << "Введите число дюймов: ";
	cin >> d.inches;
	return d;
}
void ShowDist(Distance d)
{
	cout << d.feet << "\'-" << d.inches << "\"\n";
}

void SummDist(Distance masDist[], int n)
{
	int feetSumm=0;
	double inchesSumm=0;

	for (int i = 0; i < n; i++)
	{
		feetSumm = feetSumm + masDist[i].feet;
		inchesSumm = inchesSumm + masDist[i].inches;
	}
	
	if (inchesSumm>12) {
		feetSumm = feetSumm + inchesSumm/12;
		inchesSumm = inchesSumm-12*( (int)inchesSumm/12);
	}


	cout << " Сумма длинн по массиву \n";
	cout << feetSumm << "\'-" << inchesSumm << "\"\n";
}
int main()
{
	system("chcp 1251");

	Distance d1 = InputDist();
	Distance d2 = { 1, 6.25 };
	Distance d3 = AddDist(d1, d2);

	d1.ShowDist();
	d2.ShowDist();
	d3.ShowDist();

	int n;
	cout << "Введите размер массива расстояний ";
	cin >> n;

	Distance* masDist = new Distance[n];

	for (int i = 0; i < n; i++)
	{
		masDist[i] = InputDist();
	}

	for (int i = 0; i < n; i++)
	{
		ShowDist(masDist[i]);
	}

	SummDist(masDist, n);

	delete[] masDist;
	return 0;
}*/
//--------------------------------------------------------------------------
//1
/*struct Time
{
	int hours;
	int minutes;
	double seconds;

	void ShowTime()
	{
		cout << hours << ":" << minutes << ":" << seconds << "\n";
	}

	void ShowTotalHours()
	{
		double hoursTotal;
		hoursTotal = hours + minutes / 60.+ seconds / 3600.;
		cout << "Hours: "<<hoursTotal << "\n";
	}

	void ShowTotalMinutes()
	{
		double minutesTotal;
		minutesTotal = hours * 60 + minutes + seconds / 60.;
		cout << "Minutes: " << minutesTotal << "\n";
	}

	void ShowTotalSeconds()
	{
		double secondsTotal;
		secondsTotal= hours * 60 *60 + minutes * 60 + seconds;
		cout << "Seconds: "<< secondsTotal << "\n";
	}
};
Time InputTime()
{
	Time t;
	cout << "\nВведите количество часов: ";
	cin >> t.hours;
	cout << "Введите количество минут: ";
	cin >> t.minutes;
	cout << "Введите количество секунд: ";
	cin >> t.seconds;
	return t;
}
void ShowTime(Time t)
{
	cout << t.hours << ":" << t.minutes << ":" << t.seconds << "\n";
}

Time AddTime(const Time& t1, const Time& t2)
{
	Time t;
	t.hours = t1.hours+ t2.hours;
	t.minutes = t1.minutes + t2.minutes;
	t.seconds = t1.seconds+ t2.seconds;

	if (t.seconds >= 60.0)
	{
		t.seconds -= 60.0;
		t.minutes++;
	}

	if (t.minutes >= 60.0)
	{
		t.minutes -= 60.0;
		t.hours++;
	}

	return t;
}
//------------------
Time SubtractTime(const Time& t1, const Time& t2)
{
	Time t;

	int t1Hours=t1.hours;
	int t1Minutes=t1.minutes;
	double t1Seconds=t1.seconds;

	int t2Hours = t2.hours;
	int t2Minutes = t2.minutes;
	double t2Seconds = t2.seconds;

	if (t1Seconds < t2Seconds)
	{
		t1Minutes -= 1.0;
		t1Seconds += 60.0;
	}

	if (t1Minutes < t2Minutes)
	{
		t1Hours -= 1.0;
		t1Minutes += 60.0;
	}

	t.hours = t1Hours - t2Hours;
	t.minutes = t1Minutes - t2Minutes;
	t.seconds = t1Seconds - t2Seconds;

	return t;
}
//------------------

int main()
{
	system("chcp 1251");

	Time t1 = InputTime();
	Time t2 = { 1, 55, 45 };
	Time t3 = AddTime(t1, t2);
	Time t4 = SubtractTime(t3, t1);

	t1.ShowTime();
		t1.ShowTotalHours();
		t1.ShowTotalMinutes();
		t1.ShowTotalSeconds();
	t2.ShowTime();
	t3.ShowTime();
	t4.ShowTime();

return 0;
}*/
//--------------------------------------------------------------------------
//2
struct QuadraticRoots
{
	double x1 = 0;
	double x2 = 0;

	void ShowInputQuadraticRoots()
	{
		if (x1 !=0 && (x1 == x2)) {
			cout << "Дискриминант равен 0, у уравнения есть 1 корень" << endl;
			cout << "x = " << x1 << endl;
		}
		else {
			if (x1 != x2) {
				cout << "Дискриминант больше 0, у уравнения есть 2 корня" << endl;
				cout << "x1 = " << x1 << endl;
				cout << "x2 = " << x2 << endl;
			}
			else {//d<0
				cout << "Дискриминант меньше 0, у уравнения нет корней" << endl;
			}
		}
		//cout << hours << ":" << minutes << ":" << seconds << "\n";
	}
};

QuadraticRoots InputQuadraticRoots()
{
	QuadraticRoots QR;
	cout << "Функция поиска корней квадратного уравнения" << endl;
	double a, b, c;

	cout << "a = "; cin >> a;
	cout << "b = "; cin >> b;
	cout << "c = "; cin >> c;

	double d = b * b - 4 * a * c;

	if (d >= 0) {
		QR.x1 = (-b + sqrt(d)) / (2 * a);
		QR.x2 = (-b - sqrt(d)) / (2 * a);
	}
	
	return QR;
}

int main()
{
	system("chcp 1251");

	QuadraticRoots QR1 = InputQuadraticRoots();
	QR1.ShowInputQuadraticRoots();

return 0;
}

//--------------------------------------------------------------------------