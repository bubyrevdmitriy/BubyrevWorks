// student.h
#include "mesurmentsResult.h"
#include <string>
#include <vector>
#include <sstream>
#include<fstream>
using namespace std;
class mesurmentsResultRomanovsky : public mesurmentsResult {
public:
	mesurmentsResultRomanovsky(int numberOfMesurments, std::vector<double> mesurmentsArrayEnter) : mesurmentsResult(numberOfMesurments, mesurmentsArrayEnter)
	{}

	void set_criterionRomanovsky() {
		double criterionRomanovsky;
		cout << "Type '1', if you want significance level to be 0.01. Type '2', if you want significance level to be 0.02."<<endl;
		cout << "Type '3', if you want significance level to be 0.05. Type '4', if you want significance level to be 0.10."<<endl;
		int caseSwitch;
		cin >> caseSwitch;

        switch (caseSwitch)//интерпол€ци€
        {
        case 1://0.01

            if (numberOfMesurments == 4) { criterionRomanovsky = 1.73; }//1
            if (4 < numberOfMesurments && numberOfMesurments < 6) { criterionRomanovsky = (numberOfMesurments - 4) * (2.16 - 1.73) / (6 - 4); }//2
            if (numberOfMesurments == 6) { criterionRomanovsky = 2.16; }//3
            if (6 < numberOfMesurments && numberOfMesurments < 8) { criterionRomanovsky = (numberOfMesurments - 6) * (2.43 - 2.16) / (8 - 6); }//4
            if (numberOfMesurments == 8) { criterionRomanovsky = 2.43; }//5
            if (8 < numberOfMesurments && numberOfMesurments < 10) { criterionRomanovsky = (numberOfMesurments - 8) * (2.62 - 2.43) / (10 - 8); }//6
            if (numberOfMesurments == 10) { criterionRomanovsky = 2.62; }//7
            if (10 < numberOfMesurments && numberOfMesurments < 12) { criterionRomanovsky = (numberOfMesurments - 10) * (2.75 - 2.62) / (12 - 10); }//8
            if (numberOfMesurments == 12) { criterionRomanovsky = 2.75; }//9
            if (12 < numberOfMesurments && numberOfMesurments < 15) { criterionRomanovsky = (numberOfMesurments - 12) * (2.90 - 2.75) / (15 - 12); }//10
            if (numberOfMesurments == 15) { criterionRomanovsky = 2.90; }//11
            if (15 < numberOfMesurments && numberOfMesurments < 20) { criterionRomanovsky = (numberOfMesurments - 15) * (3.08 - 2.90) / (20 - 15); }//12
            if (numberOfMesurments == 20) { criterionRomanovsky = 3.08; }//13
            break;

        case 2://0.02

            if (numberOfMesurments == 4) { criterionRomanovsky = 1.72; }//1
            if (4 < numberOfMesurments && numberOfMesurments < 6) { criterionRomanovsky = (numberOfMesurments - 4) * (2.13 - 1.72) / (6 - 4); }//2
            if (numberOfMesurments == 6) { criterionRomanovsky = 2.13; }//3
            if (6 < numberOfMesurments && numberOfMesurments < 8) { criterionRomanovsky = (numberOfMesurments - 6) * (2.37 - 2.13) / (8 - 6); }//4
            if (numberOfMesurments == 8) { criterionRomanovsky = 2.37; }//5
            if (8 < numberOfMesurments && numberOfMesurments < 10) { criterionRomanovsky = (numberOfMesurments - 8) * (2.54 - 2.37) / (10 - 8); }//6
            if (numberOfMesurments == 10) { criterionRomanovsky = 2.54; }//7
            if (10 < numberOfMesurments && numberOfMesurments < 12) { criterionRomanovsky = (numberOfMesurments - 10) * (2.66 - 2.54) / (12 - 10); }//8
            if (numberOfMesurments == 12) { criterionRomanovsky = 2.66; }//9
            if (12 < numberOfMesurments && numberOfMesurments < 15) { criterionRomanovsky = (numberOfMesurments - 12) * (2.80 - 2.66) / (15 - 12); }//10
            if (numberOfMesurments == 15) { criterionRomanovsky = 2.80; }//11
            if (15 < numberOfMesurments && numberOfMesurments < 20) { criterionRomanovsky = (numberOfMesurments - 15) * (2.96 - 2.80) / (20 - 15); }//12
            if (numberOfMesurments == 20) { criterionRomanovsky = 2.96; }//13
            break;

        case 3://0.05

            if (numberOfMesurments == 4) { criterionRomanovsky = 1.71; }//1
            if (4 < numberOfMesurments && numberOfMesurments < 6) { criterionRomanovsky = (numberOfMesurments - 4) * (2.10 - 1.71) / (6 - 4); }//2
            if (numberOfMesurments == 6) { criterionRomanovsky = 2.10; }//3
            if (6 < numberOfMesurments && numberOfMesurments < 8) { criterionRomanovsky = (numberOfMesurments - 6) * (2.27 - 2.10) / (8 - 6); }//4
            if (numberOfMesurments == 8) { criterionRomanovsky = 2.27; }//5
            if (8 < numberOfMesurments && numberOfMesurments < 10) { criterionRomanovsky = (numberOfMesurments - 8) * (2.41 - 2.27) / (10 - 8); }//6
            if (numberOfMesurments == 10) { criterionRomanovsky = 2.41; }//7
            if (10 < numberOfMesurments && numberOfMesurments < 12) { criterionRomanovsky = (numberOfMesurments - 10) * (2.52 - 2.41) / (12 - 10); }//8
            if (numberOfMesurments == 12) { criterionRomanovsky = 2.52; };//9
            if (12 < numberOfMesurments && numberOfMesurments < 15) { criterionRomanovsky = (numberOfMesurments - 12) * (2.64 - 2.52) / (15 - 12); }//10
            if (numberOfMesurments == 15) { criterionRomanovsky = 2.64; }//11
            if (15 < numberOfMesurments && numberOfMesurments < 20) { criterionRomanovsky = (numberOfMesurments - 15) * (2.78 - 2.64) / (20 - 15); }//12
            if (numberOfMesurments == 20) { criterionRomanovsky = 2.78; }//13
            break;

        case 4://0.10

            if (numberOfMesurments == 4) { criterionRomanovsky = 1.69; }//1
            if (4 < numberOfMesurments && numberOfMesurments < 6) { criterionRomanovsky = (numberOfMesurments - 4) * (2.00 - 1.69) / (6 - 4); }//2
            if (numberOfMesurments == 6) { criterionRomanovsky = 2.00; }//3
            if (6 < numberOfMesurments && numberOfMesurments < 8) { criterionRomanovsky = (numberOfMesurments - 6) * (2.17 - 2.00) / (8 - 6); }//4
            if (numberOfMesurments == 8) { criterionRomanovsky = 2.17; }//5
            if (8 < numberOfMesurments && numberOfMesurments < 10) { criterionRomanovsky = (numberOfMesurments - 8) * (2.29 - 2.17) / (10 - 8); }//6
            if (numberOfMesurments == 10) { criterionRomanovsky = 2.29; }//7
            if (10 < numberOfMesurments && numberOfMesurments < 12) { criterionRomanovsky = (numberOfMesurments - 10) * (2.39 - 2.29) / (12 - 10); }//8
            if (numberOfMesurments == 12) { criterionRomanovsky = 2.39; }//9
            if (12 < numberOfMesurments && numberOfMesurments < 15) { criterionRomanovsky = (numberOfMesurments - 12) * (2.49 - 2.39) / (15 - 12); }//10
            if (numberOfMesurments == 15) { criterionRomanovsky = 2.49; }//11
            if (15 < numberOfMesurments && numberOfMesurments < 20) { criterionRomanovsky = (numberOfMesurments - 15) * (2.62 - 2.49) / (20 - 15); }//12
            if (numberOfMesurments == 20) { criterionRomanovsky = 2.62; }//13
            break;

        default:

            cout << "Default case"<<endl;
            break;
        }
		this->criterionRomanovsky = criterionRomanovsky;
	}

    void set_mesurmentsArrayRomanovskyBeta() {
        std::vector<double> mesurmentsArrayRomanovskyBeta;
        for (int i = 0; i < numberOfMesurments; i++)
        {
            mesurmentsArrayRomanovskyBeta.push_back(abs(mesurmentsArrayDeviations[i]/standardDeviation));
        }
        this->mesurmentsArrayRomanovskyBeta = mesurmentsArrayRomanovskyBeta;
    }

    void set_mesurmentsArrayResult() {
        std::vector<double> mesurmentsArrayResult;
        int numberOfMesurmentsResult=0;
        for (int i = 0; i < numberOfMesurments; i++)
        {
            if (mesurmentsArrayRomanovskyBeta[i] > criterionRomanovsky)
            {
                mesurmentsArrayResult.push_back(0);
            }
            else
            {
                mesurmentsArrayResult.push_back(mesurmentsArrayEnter[i]);
                numberOfMesurmentsResult++;
            }            
        }
        this->numberOfMesurmentsResult = numberOfMesurmentsResult;
        this->mesurmentsArrayResult = mesurmentsArrayResult;
    }

    void set_mesurmentsResultResult() {

        double mesurmentsArrayResultSumm = 0;
        double mesurmentsResultResult;

        for (int i = 0; i < numberOfMesurments; i++)
        {
            mesurmentsArrayResultSumm = mesurmentsArrayResultSumm + mesurmentsArrayResult[i];
        }
        mesurmentsResultResult = mesurmentsArrayResultSumm / numberOfMesurmentsResult;

        this->mesurmentsResultResult = mesurmentsResultResult;
    }

    void print() {
        cout << "Results in tabular form" << endl;
        cout << "Program changes Gross errors to 0" << endl;
        cout << endl;

        cout << " i | Array | Deviations | DeviationsSquared | Beta | Result" << endl;
        cout << "--------------------------------------------------------------------------------------" << endl;

        for (int i = 0; i < numberOfMesurments; i++)
        {
            cout << i + 1 << "   |   " << mesurmentsArrayEnter[i] << "   |   " << mesurmentsArrayDeviations[i] << "   |   " << mesurmentsArrayDeviationsSquared[i] << "   |   " << mesurmentsArrayRomanovskyBeta[i] <<"   |   " <<mesurmentsArrayResult[i] << endl; //, i + 1, Math.Round(measurmentsArray[i], 6), Math.Round(measurmentsArrayRomanovskyBeta[i], 6), Math.Round(measurmentsArrayRomanovskyResult[i], 6));
            cout << "--------------------------------------------------------------------------------------" << endl;
        }
        cout << "Average enter element= " << mesurmentsArrayEnterAverage << endl;
        cout << "criterionRomanovsky= "<< criterionRomanovsky << endl;
        cout << "Average element after gross errors deleted= "<< mesurmentsResultResult << endl;
    }

    void printToFile(string fileNamePart) {
        stringstream ss;
        ss << fileNamePart << ".txt";
        string fileName = ss.str();
        fstream f;        
        f.open(fileName, ios::out);
        f << "criterionRomanovsky" << endl;
        f << "Results in tabular form" << endl;
        f << "Program changes Gross errors to 0" << endl;
        f << endl;

        f << " i | Array | Deviations | DeviationsSquared | Beta | Result" << endl;
        f << "--------------------------------------------------------------------------------------" << endl;

        for (int i = 0; i < numberOfMesurments; i++)
        {
            f << i + 1 << "   |   " << mesurmentsArrayEnter[i] << "   |   " << mesurmentsArrayDeviations[i] << "   |   " << mesurmentsArrayDeviationsSquared[i] << "   |   " << mesurmentsArrayRomanovskyBeta[i] << "   |   " << mesurmentsArrayResult[i] << endl; //, i + 1, Math.Round(measurmentsArray[i], 6), Math.Round(measurmentsArrayRomanovskyBeta[i], 6), Math.Round(measurmentsArrayRomanovskyResult[i], 6));
            f << "--------------------------------------------------------------------------------------" << endl;
        }
        f << "Average enter element= " << mesurmentsArrayEnterAverage << endl;
        f << "criterionRomanovsky= " << criterionRomanovsky << endl;
        f << "Average element after gross errors deleted= " << mesurmentsResultResult << endl;
        f.close();
    }


private:
	double criterionRomanovsky;
	std::vector<double> mesurmentsArrayRomanovskyBeta;
};