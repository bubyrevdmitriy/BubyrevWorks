// main.cpp
#include <iostream>
#include <vector>
#include "human.h"
#include "student.h"
#include "teacher.h"

using namespace std;
int main()
{
	system("chcp 1251");

	std::cout << "������������ ������ ������ student" << std::endl;
	// ��������������
	std::vector<int> scores;
	// ���������� ������ �������� � ������
	scores.push_back(5);
	scores.push_back(3);
	scores.push_back(4);
	scores.push_back(4);
	scores.push_back(5);
	scores.push_back(3);
	scores.push_back(3);
	scores.push_back(3);
	scores.push_back(3);
	
	student* stud = new student("������", "����", "����������", scores);

	std::cout << stud->get_full_name() << std::endl;

	std::cout << "������� ���� : " << stud->get_average_score() << std::endl;
	
	
	std::cout << "������������ ������ ������ teacher" << std::endl;
	
	unsigned int teacher_work_time = 40;
	teacher* tch = new teacher("�������", "�������", "���������", teacher_work_time);
	std::cout << tch->get_full_name() << std::endl;
	std::cout << "���������� �����: " << tch->get_work_time() << std::endl;
	
	std::cout << "������������ ������ ������� student � teacher � ����������� ������������" << std::endl;
	//SetConsoleOutputCP(1251);
	human* pubarr[100];
	int n = 0;
	char choice;
	do
	{
		cout << "\n������� ������ ��� student ��� teacher(s / t) ? ";
		cin >> choice;
		

		if (choice == 's') {
		
			pubarr[n] = new student();
		}
		else {
			
			pubarr[n] = new teacher();
		}
		pubarr[n++]->getdata();
		cout << "����������((� / n) ? ";
		cin >> choice;
	} while (choice == 'y');

	for (int j = 0; j < n; j++) //���� �� ���� ��������
		 pubarr[j]->putdata(); //������� ������ � ����������
	cout << endl;


	return 0;
}

