
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
	cout << "������� �������� ���������� X ����� �1:\n";
	cin >> x;
	cout << "\n������� �������� ���������� Y ����� �1:\n";
	cin >> y;
	
	if (x * x + y * y < 9 && y > 0)
		result = "������";
	else if (x * x + y * y > 9 || y < 0)
		result = "�������";
	else 
		result = "�� �������";
	
	cout << "\n����� ����� " << result << endl;

	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	char op;
	cout << "������ ���� �����, ������ ���� ���� ����� (S ��� V): ";
	cin >> op;
	switch (op)
	{
	case 'V':
		cout << "����������� ����\n";
	case 'S':
		cout << "����� ������ ������\n";
	default:
		cout << "������ �������\n";
		cout << "������ ���������\n";
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
			a -= b; // ���������� ��������� a = a - b
		else
			b -= a;
	}

	cout << "��� = " << a << endl;
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
			a -= b; // ���������� ��������� a = a - b
		else
			b -= a;
	} while ((a - b) != (b - a));

	cout << "��� = " << a << endl;
	return 0;
}*/
//-------------------------------
/*int main()
{
	system("chcp 1251");
	srand(time(NULL)); 
	int a, b, c;
	int k = 0, n = 10;

	for (int i = 1; i <= n; i++) { // ������������� ��������� ���������� ������� �� 1 �� 101 
		a = rand() % 10 + 1;
		b = rand() % 10 + 1;
		cout << a << " * " << b << " = ";
		cin >> c;
		if (a * b != c)
		{
			k++; // �������� ����������, ����������: k = k + 1
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
	cout << "��� = "; cin >> year;
	if ((year % 4 == 0)&&(year % 100 != 0)|| (year % 400 == 0)) {
		result="����������";
	}
	else {
		result = "�� ����������";
	}
	cout << "��� ��� " << result << endl;
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

	cout << "����� ������� ����� = " << result << endl;
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
	int precision = 2; // ���������� ������ ����� �������
	rub = (int)money;
	cop = (int)((money - floor(money)) * pow(10, precision) + 0.01);

		
		cout << "������ = " << rub << endl;
		cout << "������ = " << cop << endl;

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
		cout << "���������� ����� � 10 ������ = " << tenRub << endl;
	}
	if (fiveRub > 0) { 
		cout << "���������� ����� � 5 ������ = " << fiveRub << endl;
	}
	if (twoRub > 0) { 
		cout << "���������� ����� � 2 ����� = " << twoRub << endl;
	}
	if (oneRub > 0) { 
		cout << "���������� ����� � 1 ����� = " << oneRub << endl;
	}

	if (fiftyCop > 0) { 
		cout << "���������� ����� � 50 ������� = " << fiftyCop << endl;
	}
	if (tenCop > 0) { 
		cout << "���������� ����� � 10 ������� = " << tenCop << endl;
	}
	if (fiveCop > 0) { 
		cout << "���������� ����� � 5 ������� = " << fiveCop << endl;
	}
	if (oneCope > 0) { 
		cout << "���������� ����� � 1 ������� = " << oneCope << endl;
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
		cout << "������� �������� ���������� X ����� �"<<i+1<<":\n";
		cin >> arrX[i];
		cout << "\n������� �������� ���������� Y ����� �"<<i+1<<":\n";
		cin >> arrY[i];
	}

	for (int i = 0; i < 3; i++) {//���������� ��������� = 3
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
		cout << "��������� �������� �" << i + 1 <<" - "<< arrResult[i] <<":\n";		
	}
	cout << "����� ������� " << resultResult << " ����� \n";
	
	if (resultResult / 3 > 8) {
		cout << "������� ���������� - ������ \n";
	}
	else {
		if (resultResult / 3 > 5) {
			cout << "������� ���������� - ������� �������  \n";
		}
		else {
			cout << "�������� ���������� - ��������� �������  \n";
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

	cout << "������� �������� ���������� ��� ������:\n";
	cin >> requiredResult;

	while (currentResult< requiredResult)
	{
		count++;
		cout << "������� �" << count << " - :\n";

		cout << "������� �������� ���������� X ����� �" << count << ":\n";
		cin >> x;
		cout << "\n������� �������� ���������� Y ����� �" << count << ":\n";
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
		cout << "��������� �������� �" <<count << " - " << shootResult << ":\n";

	}

	cout << "����� ������� " << currentResult << " ����� \n";

	if (currentResult / count > 8) {
		cout << "������� ���������� - ������ \n";
	}
	else {
		if (currentResult / count > 5) {
			cout << "������� ���������� - ������� �������  \n";
		}
		else {
			cout << "�������� ���������� - ��������� �������  \n";
		}
	}

	return 0;
}