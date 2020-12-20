// human.h
#include <string>
#include <sstream>
#pragma once /* ������ �� �������� ����������� ������������� ����� */
class human {
public:
	// ����������� ������ human
	human(std::string last_name, std::string name, std::string second_name)
	{
		this->last_name = last_name;
		this->name = name;
		this->second_name = second_name;
	}

	human()	{}

	// ��������� ��� ��������
	std::string get_full_name()
	{
		std::ostringstream full_name;
		full_name << this->last_name << " "
			<< this->name << " "
			<< this->second_name;
		return full_name.str();
	}

	virtual void getdata()
	{
		std::cout << "\n������� Last name : ";
		std::cin >> last_name;
		std::cout << "������� Name : ";
		std::cin >> name;
		std::cout << "������� Second name : ";
		std::cin >> second_name;
	}
	virtual void putdata()
	{
		std::cout << "\nLast name: " << last_name;
		std::cout << "\nName:" << name;
		std::cout << "\nSecond name:" << second_name;
	}
private:
	std::string name; // ���
	std::string last_name; // �������
	std::string second_name; // ��������
};