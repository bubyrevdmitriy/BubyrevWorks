/*#include <iostream> #include <string>
using namespace std;
class DivideByZeroError 
{ 
public: DivideByZeroError() : message("������� �� ����") { } 
void printMessage() const { cout << message << endl; }
private: 
	string message; 
};

float quotient(int numl, int num2) 
{ if (num2 == 0) 
throw DivideByZeroError();
return (float)numl / num2; 
}*/


/*int main() {
	system("chcp 1251");
	cout << "������� ��� ����� ����� ��� ������� �� ��������:\n";
	int number1, number2; 
	cin >> number1; cin >> number2; 
	try { 
		float result = quotient(number1, number2); 
		cout << "������� ����� " << result << endl; 
	}
	catch (DivideByZeroError& error) {
		cout << "������: "; 
		error.printMessage(); 
		return 1; // ���������� ��������� ��� ������ 
	} return 0; // ���������� ���������� ��������� 
}*/

/*int main() {
	system("chcp 1251"); 
	cout << "���������� ������� � �����\n";
	cout << "������� ���������:\n";
	int number1;
	cin >> number1;

	for (int i = -10; i < 10; i++) {
		try
		{
			float result = quotient(number1, i);
			cout << "������� ������� "<< number1 <<" �� "<< i <<" ����� " << result << endl;
		}
		catch (DivideByZeroError& error)
		{
			cout << "������: "; error.printMessage();
		}
	}
		return 0; // ���������� ���������� ���������
}*/