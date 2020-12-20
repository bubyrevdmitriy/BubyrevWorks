
#include <iostream>
#include <string>
#include<math.h>
#include <ctime>
using namespace std;

/*int main()
{
	system("chcp 1251");
	string result;
	double x, y;
	cout << "Введите значение координаты X точки №1:\n";
	cin >> x;
	cout << "\nВведите значение координаты Y точки №1:\n";
	cin >> y;
	
	if (x * x + y * y < 9 && y > 0)
		result = "внутри";
	else if (x * x + y * y > 9 || y < 0)
		result = "снаружи";
	else 
		result = "на границе";
	
	cout << "\nТочка лежит " << result << endl;

	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	char op;
	cout << "Сделай свой выбор, собери авто свой мечты (S или V): ";
	cin >> op;
	switch (op)
	{
	case 'V':
		cout << "Кондиционер хочу\n";
	case 'S':
		cout << "Радио играть должно\n";
	default:
		cout << "Колеса круглые\n";
		cout << "Мощный двигатель\n";
	}
	return 0;
}*/

/*int main()
{
	double x, x1, x2, y;
	cout << "x1 = "; cin >> x1;
	cout << "x2 = "; cin >> x2;
	cout << "\tx\tsin(x)\n";
	x = x1;
	do
	{
		y = sin(x);
		cout << "\t" << x << "\t" << y << endl;
		x = x + 0.01;
	} while (x <= x2);
	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	int a, b, temp;
	cout << "a = "; cin >> a;
	cout << "b = "; cin >> b;

	while (a != b)
	{
		if (a > b)
			a -= b; // аналогично выражению a = a - b
		else
			b -= a;
	}

	cout << "НОД = " << a << endl;
	return 0;
}*/
//-------------------------------
/*int main()
{
	double x, x1, x2, y;
	cout << "x1 = "; cin >> x1;
	cout << "x2 = "; cin >> x2;
	cout << "\tx\tsin(x)\n";
	x = x1;
	while (x <=x2 + 0.01)
	{
		y = sin(x);
		cout << "\t" << x << "\t" << y << endl;
		x = x + 0.01;
	} 
	return 0;
}*/
//-------------------------------
/*int main()
{
	system("chcp 1251");
	int a, b, temp;
	cout << "a = "; cin >> a;
	cout << "b = "; cin >> b;

	do
	{
		if (a > b)
			a -= b; // аналогично выражению a = a - b
		else
			b -= a;
	} while ((a - b) != (b - a));

	cout << "НОД = " << a << endl;
	return 0;
}*/
//-------------------------------
/*int main()
{
	system("chcp 1251");
	srand(time(NULL)); 
	int a, b, c;
	int k = 0, n = 10;

	for (int i = 1; i <= n; i++) { // инициализация операндов случайными числами от 1 до 101 
		a = rand() % 10 + 1;
		b = rand() % 10 + 1;
		cout << a << " * " << b << " = ";
		cin >> c;
		if (a * b != c)
		{
			k++; // операция «инкремент», аналогично: k = k + 1
			cout << "Error! ";
			cout << a << " * " << b << " = " << a * b << endl;
		}
	}

	cout << "Count error: " << k << endl;
	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	int k, m, s=0;
	cout << "k = "; cin >> k;
	cout << "m = "; cin >> m;
	for (int i = 1; i <= 100; i++)
	{
		if ((i > k) && (i < m))
			continue;
		s += i;
	}
	cout << "S = " << s << endl;
	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	int year;
	string result;
	cout << "год = "; cin >> year;
	if ((year % 4 == 0)&&(year % 100 != 0)|| (year % 400 == 0)) {
		result="високосный";
	}
	else {
		result = "не високосный";
	}
	cout << "наш год " << result << endl;
	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	int a, b, c;
	int result;
	cout << "a = "; cin >> a;
	cout << "b = "; cin >> b;
	cout << "c = "; cin >> c;

	if (a>b) {
		if (a > c) { result = a; }
		else { result = c; }
	}
	else {
		if (b > c) { result = b; }
		else { result = c; }
	}

	cout << "Самое большое число = " << result << endl;
	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	double money;
	int rub=0, cop=0;
	int tenRub=0, fiveRub=0, twoRub=0, oneRub=0;
	int fiftyCop=0, tenCop=0, fiveCop=0, oneCope=0;
	cout << "money = "; cin >> money;
	//cout << "rub = "; cin >> rub;
	//cout << "cop = "; cin >> cop;

	//double dbl = 123.456789;
	int precision = 2; // количество знаков после запятой
	rub = (int)money;
	cop = (int)((money - floor(money)) * pow(10, precision) + 0.01);

		
		cout << "рублей = " << rub << endl;
		cout << "копеек = " << cop << endl;

	if (rub/10>0) {
		tenRub = rub/10;
		rub = rub - tenRub*10;
	}
	if (rub/5>0) {
		fiveRub = rub / 5;
		rub = rub - fiveRub*5;
	}
	if (rub/2>0) {
		twoRub = rub / 2;
		rub = rub - twoRub*2;
	}
	if (rub/1>0) {
		oneRub = rub / 1;
		rub = rub - oneRub*1;
	}
	//-------------------------------------------------
	if (cop / 50 > 0) {
		fiftyCop = cop / 50;
		cop = cop - fiftyCop * 50;
	}
	if (cop / 10 > 0) {
		tenCop = cop / 10;
		cop = cop - tenCop * 10;
	}
	if (cop / 5 > 0) {
		fiveCop = cop / 5;
		cop = cop - fiveCop * 5;
	}
	if (rub / 1 > 0) {
		oneCope = cop / 1;
		cop = cop - oneCope * 1;
	}
	//----------------------------------------------------------------------
	if (tenRub > 0) { 
		cout << "Количество монет в 10 рублей = " << tenRub << endl;
	}
	if (fiveRub > 0) { 
		cout << "Количество монет в 5 рублей = " << fiveRub << endl;
	}
	if (twoRub > 0) { 
		cout << "Количество монет в 2 рубля = " << twoRub << endl;
	}
	if (oneRub > 0) { 
		cout << "Количество монет в 1 рубль = " << oneRub << endl;
	}

	if (fiftyCop > 0) { 
		cout << "Количество монет в 50 копейку = " << fiftyCop << endl;
	}
	if (tenCop > 0) { 
		cout << "Количество монет в 10 копейку = " << tenCop << endl;
	}
	if (fiveCop > 0) { 
		cout << "Количество монет в 5 копейку = " << fiveCop << endl;
	}
	if (oneCope > 0) { 
		cout << "Количество монет в 1 копейку = " << oneCope << endl;
	}

	return 0;
}*/


/*int main()
{
	system("chcp 1251");
	double arrX[3];
	double arrY[3];
	int arrResult[3];


	for (int i = 0; i < 3; i++) { 
		cout << "Введите значение координаты X точки №"<<i+1<<":\n";
		cin >> arrX[i];
		cout << "\nВведите значение координаты Y точки №"<<i+1<<":\n";
		cin >> arrY[i];
	}

	for (int i = 0; i < 3; i++) {//количество выстрелов = 3
		bool milk;
		bool onePoint;
		bool fivePoint;
		bool tenPoint;
		
		double x = arrX[i];
		double y = arrY[i];
		if (x * x + y * y <= 1 * 1 ) {
			arrResult[i] = 10;
		}
		else {
			if (x * x + y * y <= 2 * 2 ) {
				arrResult[i] = 5;
			}
			else {
				if (x * x + y * y <= 3 * 3) {
					arrResult[i] = 1;
				}
				else{
					arrResult[i] = 0;
				}
			}
		}

	}

	double resultResult=0;
	for (int i = 0; i < 3; i++) {
		resultResult = resultResult + arrResult[i];
		cout << "Результат выстрела №" << i + 1 <<" - "<< arrResult[i] <<":\n";		
	}
	cout << "Всего набрано " << resultResult << " очков \n";
	
	if (resultResult / 3 > 8) {
		cout << "Уровень мастерства - Мастер \n";
	}
	else {
		if (resultResult / 3 > 5) {
			cout << "Уровень мастерства - средний уровень  \n";
		}
		else {
			cout << "Уровеньь мастерства - начальный уровень  \n";
		}
	}
	return 0;
}*/

int main()
{
	system("chcp 1251");
	double x;
	double y;
	int requiredResult;
	int currentResult = 0;
	int shootResult=0;
	int count=0;
	int result;

	cout << "Введите значение результата для победы:\n";
	cin >> requiredResult;

	while (currentResult< requiredResult)
	{
		count++;
		cout << "Выстрел №" << count << " - :\n";

		cout << "Введите значение координаты X точки №" << count << ":\n";
		cin >> x;
		cout << "\nВведите значение координаты Y точки №" << count << ":\n";
		cin >> y;


		if (x * x + y * y <= 1 * 1) {
			shootResult = 10;
		}
		else {
			if (x * x + y * y <= 2 * 2) {
				shootResult = 5;
			}
			else {
				if (x * x + y * y <= 3 * 3) {
					shootResult = 1;
				}
				else {
					shootResult = 0;
				}
			}
		}

		currentResult = currentResult + shootResult;
		cout << "Результат выстрела №" <<count << " - " << shootResult << ":\n";

	}

	cout << "Всего набрано " << currentResult << " очков \n";

	if (currentResult / count > 8) {
		cout << "Уровень мастерства - Мастер \n";
	}
	else {
		if (currentResult / count > 5) {
			cout << "Уровень мастерства - средний уровень  \n";
		}
		else {
			cout << "Уровеньь мастерства - начальный уровень  \n";
		}
	}

	return 0;
}