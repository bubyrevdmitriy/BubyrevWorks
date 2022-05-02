from builtins import print
from math import sqrt
from abc import ABC, abstractmethod
from tkinter import *

# Задание 2.6
# Интерфейс

###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс
from tkinter import scrolledtext
from tkinter.ttk import Radiobutton


def get_first_name(self):
    return self.first_name


def get_last_name(self):
    return self.last_name


def get_middle_name(self):
    return self.middle_name


def init(self, first_name, last_name, middle_name):
    self.first_name = first_name
    self.last_name = last_name
    self.middle_name = middle_name


class BaseUser(object):
    def __str__(self):
        return '<user-object/>'


attrs = {
    'first_name': '',
    'last_name': '',
    'middle_name': '',
    'get_first_name': get_first_name,
    'get_last_name': get_last_name,
    'get_middle_name': get_middle_name,
    '__init__': init
}

bases = (
    BaseUser,
)


###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс

# Декоратор
def method_friendly_decorator(method_to_decorate):
    def wrapper(self):
        print("Run method: " + str(method_to_decorate.__name__))
        return method_to_decorate(self)

    return wrapper


# Класс миксин, проверяем из какого именно класса-миксина будет наследоваться метод
class Mixin1(object):
    def whereDevelope(self):
        print("The program was developed in the glorious city of Kudrovo!")


# Класс миксин, проверяем из какого именно класса-миксина будет наследоваться метод
class Mixin2(object):
    def whereDevelope(self):
        print("The program was developed in the glorious city of St. Petersburg")


class AuthorInformation:
    def __init__(self, inf):
        self.__inf = inf

    def getInf(self):
        return "Author:" + str(self.__inf)

    def printInf(self):
        print(self.__inf)


class mesurmentsResult(ABC):
    __numberOfMesurments = 1
    __mesurmentsArrayEnter = []
    __mesurmentsArrayEnterAverage = 1
    __mesurmentsArrayDeviations = []
    __mesurmentsArrayDeviationsSquared = []
    __mesurmentsArrayDeviationsSquaredSumm = 1
    __standardDeviation = 1
    __mesurmentsArrayResult = []
    __mesurmentsResultResult = 1
    __numberOfMesurmentsResult = 1
    __inf = ""

    @abstractmethod
    def set_mesurmentsArrayResult(self):
        pass

    @abstractmethod
    def set_mesurmentsResultResult(self):
        pass

    @abstractmethod
    def printClass(self):
        pass

    @abstractmethod
    def printClassToFile(self, dirfile, namefile):
        pass

    def printAuthorInformation(self):
        print(self.__AuthorInformation.getInf())

    def __init__(self, measurementsNumber, measurementsArray, inf):
        self.__numberOfMesurments = measurementsNumber
        self.__mesurmentsArrayEnter = measurementsArray
        self.__inf = inf
        self.__AuthorInformation = AuthorInformation(inf)

    def getNumberOfMesurments(self):
        return self.__numberOfMesurments

    def getMesurmentsArrayEnter(self):
        return self.__mesurmentsArrayEnter

    def getMesurmentsArrayEnterAverage(self):
        return self.__mesurmentsArrayEnterAverage

    def getMesurmentsArrayDeviations(self):
        return self.__mesurmentsArrayDeviations

    def getMesurmentsArrayDeviationsSquared(self):
        return self.__mesurmentsArrayDeviationsSquared

    def getMesurmentsArrayDeviationsSquaredSumm(self):
        return self.__mesurmentsArrayDeviationsSquaredSumm

    def getStandardDeviation(self):
        return self.__standardDeviation

    def getMesurmentsArrayResult(self):
        return self.__mesurmentsArrayResult

    def getMesurmentsResultResult(self):
        return self.__mesurmentsResultResult

    def getNumberOfMesurmentsResult(self):
        return self.__numberOfMesurmentsResult

    def getInf(self):
        return self.__inf

    def set_mesurmentsArrayEnterAverage(self):
        mesurmentsArrayEnterSumm = 0
        for i in self.getMesurmentsArrayEnter():
            mesurmentsArrayEnterSumm = mesurmentsArrayEnterSumm + i
        mesurmentsArrayEnterAverage = mesurmentsArrayEnterSumm / self.getNumberOfMesurments()
        self.__mesurmentsArrayEnterAverage = mesurmentsArrayEnterAverage

    def set_mesurmentsArrayDeviations(self):
        mesurmentsArrayDeviations = []
        for i in self.getMesurmentsArrayEnter():
            mesurmentsArrayDeviations.append(i - self.getMesurmentsArrayEnterAverage())
        self.__mesurmentsArrayDeviations = mesurmentsArrayDeviations

    def set_mesurmentsArrayDeviationsSquared(self):
        mesurmentsArrayDeviationsSquared = []
        for i in self.getMesurmentsArrayDeviations():
            mesurmentsArrayDeviationsSquared.append(i * i)
        self.__mesurmentsArrayDeviationsSquared = mesurmentsArrayDeviationsSquared

    def set_mesurmentsArrayDeviationsSquaredSumm(self):
        mesurmentsArrayDeviationsSquaredSumm = 0
        for i in self.getMesurmentsArrayDeviationsSquared():
            mesurmentsArrayDeviationsSquaredSumm = mesurmentsArrayDeviationsSquaredSumm + i
        self.__mesurmentsArrayDeviationsSquaredSumm = mesurmentsArrayDeviationsSquaredSumm

    def set_standardDeviation(self):

        standardDeviation = sqrt(self.getMesurmentsArrayDeviationsSquaredSumm() / (self.getNumberOfMesurments() - 1))
        self.__standardDeviation = standardDeviation

    def set_mesurmentsArrayResult(self, mesurmentsArrayResult):
        self.__mesurmentsArrayResult = mesurmentsArrayResult

    def set_mesurmentsResultResult(self, mesurmentsResultResult):
        self.__mesurmentsResultResult = mesurmentsResultResult

    def set_numberOfMesurmentsResult(self, numberOfMesurmentsResult):
        self.__numberOfMesurmentsResult = numberOfMesurmentsResult

    def makeBeatifulMesurmentsResult(self):
        self.set_mesurmentsArrayEnterAverage()
        self.set_mesurmentsArrayDeviations()
        self.set_mesurmentsArrayDeviationsSquared()
        self.set_mesurmentsArrayDeviationsSquaredSumm()
        self.set_standardDeviation()
        # print("красиво сделано")
        # print("self.getMesurmentsArrayEnter()", self.getMesurmentsArrayEnter())
        # print("self.getMesurmentsArrayEnterAverage()", self.getMesurmentsArrayEnterAverage())
        # print("self.getMesurmentsArrayDeviations()", self.getMesurmentsArrayDeviations())
        # print("self.getMesurmentsArrayDeviationsSquared()", self.getMesurmentsArrayDeviationsSquared())
        # print("self.getMesurmentsArrayDeviationsSquaredSumm()", self.getMesurmentsArrayDeviationsSquaredSumm())
        # print("self.getStandardDeviation()", self.getStandardDeviation())


################################################################################################################################################################################
class mesurmentsResultCharlier(mesurmentsResult, Mixin1, Mixin2):
    # больше 20
    __criterionCharlier = 0
    __KS = 0

    def getCriterionCharlier(self):
        return self.__criterionCharlier

    def getKS(self):
        return self.__KS

    def __init__(self, measurementsNumber, measurementsArray, inf):
        super().__init__(measurementsNumber, measurementsArray, inf)

    def __add__(self, other):
        if isinstance(other, self.__class__):
            if (self.getNumberOfMesurments() + other.getNumberOfMesurments() > 20):
                return mesurmentsResultCharlier(self.getNumberOfMesurments() + other.getNumberOfMesurments(),
                                                self.getMesurmentsArrayEnter() + other.getMesurmentsArrayEnter(),
                                                self.getInf())
            else:
                print("Воспользуйтесь критерием Романовского")
        else:
            raise TypeError("Не могу добавить {1} к {0}".
                            format(self.__class__, type(other)))

    def __subtract__(self, other):
        if isinstance(other, self.__class__):
            if (self.getNumberOfMesurments() - other.getNumberOfMesurments() > 1):
                if (self.getNumberOfMesurments() - other.getNumberOfMesurments() > 20):
                    return mesurmentsResultCharlier(self.getNumberOfMesurments() - other.getNumberOfMesurments(),
                                                    self.getMesurmentsArrayEnter() - other.getMesurmentsArrayEnter(),
                                                    self.getInf())
                else:
                    print("Воспользуйтесь критерием романовского")
            else:
                print("Не могу вычесть {1} из {0}".format(self.getNumberOfMesurments(), other.getNumberOfMesurments()))
        else:
            TypeError("Не могу вычесть {1} из {0}".
                      format(self.__class__, type(other)))

    def set_criterionCharlier(self):

        numberOfMesurments = super().getNumberOfMesurments()

        if numberOfMesurments <= 5:
            criterionCharlier = 1.3
        elif (5 < numberOfMesurments and numberOfMesurments < 10):
            criterionCharlier = (numberOfMesurments - 5) * (1.65 - 1.3) / (10 - 5)
        elif numberOfMesurments == 10:
            criterionCharlier = 1.65
        elif 10 < numberOfMesurments and numberOfMesurments < 20:
            criterionCharlier = (numberOfMesurments - 10) * (1.96 - 1.65) / (20 - 10)
        elif (numberOfMesurments == 20):
            criterionCharlier = 1.96  # 5
        elif (20 < numberOfMesurments and numberOfMesurments < 30):
            criterionCharlier = (numberOfMesurments - 20) * (2.13 - 1.96) / (30 - 20)
        elif (numberOfMesurments == 30):
            criterionCharlier = 2.13  # 7
        elif (30 < numberOfMesurments and numberOfMesurments < 40):
            criterionCharlier = (numberOfMesurments - 30) * (2.24 - 2.13) / (40 - 30)
        elif (numberOfMesurments == 40):
            criterionCharlier = 2.24  # 9
        elif (40 < numberOfMesurments and numberOfMesurments < 50):
            criterionCharlier = (numberOfMesurments - 40) * (2.32 - 2.24) / (50 - 40)
        elif (numberOfMesurments == 50):
            criterionCharlier = 2.32  # 11
        elif (50 < numberOfMesurments and numberOfMesurments < 100):
            criterionCharlier = (numberOfMesurments - 50) * (2.58 - 2.32) / (100 - 50)
        else:
            criterionCharlier = 2.58

        self.__criterionCharlier = criterionCharlier

    def set_KS(self):
        KS = self.getCriterionCharlier() * super().getStandardDeviation()
        self.__KS = KS

    def set_mesurmentsArrayResult(self):
        mesurmentsArrayResult = []
        numberOfMesurmentsResult = 0
        count = 0
        for i in self.getMesurmentsArrayDeviations():
            if (i > self.getKS()):
                mesurmentsArrayResult.append(0)
            else:

                mesurmentsArrayResult.append(self.getMesurmentsArrayEnter()[count])
                numberOfMesurmentsResult = numberOfMesurmentsResult + 1
            count = count + 1

        # self.__numberOfMesurmentsResult = numberOfMesurmentsResult
        # self.__mesurmentsArrayResult = mesurmentsArrayResult
        super().set_numberOfMesurmentsResult(numberOfMesurmentsResult)
        # numberOfMesurmentsResult = numberOfMesurmentsResult
        # self.__mesurmentsArrayResult = mesurmentsArrayResult
        super().set_mesurmentsArrayResult(mesurmentsArrayResult)

    def set_mesurmentsResultResult(self):

        mesurmentsArrayResultSumm = 0

        for i in self.getMesurmentsArrayResult():
            mesurmentsArrayResultSumm = mesurmentsArrayResultSumm + i

        mesurmentsResultResult = mesurmentsArrayResultSumm / self.getNumberOfMesurmentsResult()
        # self.__mesurmentsResultResult = mesurmentsResultResult
        super().set_mesurmentsResultResult(mesurmentsResultResult)

    def printClass(self):
        numberOfMesurments = self.getNumberOfMesurments()
        print("Charlier criterion")
        print()
        print("Program changes Gross errors to 0")
        print()

        print("i".ljust(10), "Array".ljust(20), "Deviations".ljust(20), "DeviationsSquared".ljust(20),
              "Result".ljust(20))
        print("----------------------------------------------------------------------------------")

        i = 0
        while i < numberOfMesurments:
            print(str(i + 1).ljust(10),
                  str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            print("----------------------------------------------------------------------------------")
            i = i + 1

        print("Average enter element= ", self.getMesurmentsArrayEnterAverage())
        print("criterionCharlier= ", self.getCriterionCharlier())
        print("KS= ", self.getKS())
        print("Average element after gross errors deleted= ", self.getMesurmentsResultResult())

    def printClassToList(self):
        listOfFullInformation = []
        numberOfMesurments = self.getNumberOfMesurments()
        listOfFullInformation.append("Charlier criterion")
        listOfFullInformation.append("")
        listOfFullInformation.append("Program changes Gross errors to 0")
        listOfFullInformation.append("")

        listOfFullInformation.append("i".ljust(10) + "Array".ljust(20) + "Deviations".ljust(20) + "DeviationsSquared".ljust(20) +
              "Result".ljust(20))
        listOfFullInformation.append("----------------------------------------------------------------------------------")

        i = 0
        while i < numberOfMesurments:
            listOfFullInformation.append(str(i + 1).ljust(10) +
                  str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20) +
                  str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20) +
                  str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20) +
                  str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            listOfFullInformation.append("----------------------------------------------------------------------------------")
            i = i + 1

        listOfFullInformation.append("Average enter element= " + str(self.getMesurmentsArrayEnterAverage()))
        listOfFullInformation.append("criterionCharlier= " + str(self.getCriterionCharlier()))
        listOfFullInformation.append("KS= " + str(self.getKS()))
        listOfFullInformation.append("Average element after gross errors deleted= " + str(self.getMesurmentsResultResult()))
        return listOfFullInformation

    def printClassToFile(self, dirfile, namefile):

        # Работа с файлом(запись в файл):

        namefile = dirfile + namefile  # полный путь к файлу
        myfile = open(namefile, 'w')  # Открывает файл (создает/очищает)

        myfile.write("Charlier criterion")
        myfile.write('\n')
        myfile.write("Results in tabular form")
        myfile.write('\n')
        myfile.write("Program changes Gross errors to 0")
        myfile.write('\n')

        myfile.write("i".ljust(10) + "Array".ljust(20) + "Deviations".ljust(20) + "DeviationsSquared".ljust(20) +
                     "Result".ljust(20))
        myfile.write("----------------------------------------------------------------------------------")

        i = 0
        while i < self.getNumberOfMesurments():
            myfile.write(str(i + 1).ljust(10)
                         + str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20)
                         + str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20)
                         + str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20)
                         + str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            myfile.write('\n')
            myfile.write("----------------------------------------------------------------------------------")
            myfile.write('\n')
            i = i + 1

        myfile.write("Average enter element= " + str(self.getMesurmentsArrayEnterAverage()))
        myfile.write('\n')
        myfile.write("criterionCharlier= " + str(self.getCriterionCharlier()))
        myfile.write('\n')
        myfile.write("KS= " + str(self.getKS()))
        myfile.write('\n')
        myfile.write("Average element after gross errors deleted= " + str(self.getMesurmentsResultResult()))

        myfile.close()
        # def printClassToFile(self):

        # coding: utf-8
        # Ввод списка с клавиатуры:
        # list1 = list(map(int, input().split()))
        # Работа с файлом(запись в файл):
        # dirfile = "D:\\data\\"    # можете заменить на свой каталог
        # namefile = dirfile + "myfile1.txt"    # полный путь к файлу
        # myfile = open(namefile, 'w')     # Открывает файл (создает/очищает)
        # for it in list1:          # Запись всех строк из списка в файл
        #    myfile.write(str(it))
        #    myfile.write('\n')
        # myfile.close()

    #@method_friendly_decorator
    def makeAllWork(self):
        self.makeBeatifulMesurmentsResult()
        self.set_criterionCharlier()
        self.set_KS()
        self.set_mesurmentsArrayResult()
        self.set_mesurmentsResultResult()
        #self.printClass()


###############################################################################################################################################################################
class mesurmentsResultRomanovsky(mesurmentsResult, Mixin1, Mixin2):
    # меньше 20
    __criterionRomanovsky = 0
    __mesurmentsArrayRomanovskyBeta = []

    def getCriterionRomanovsky(self):
        return self.__criterionRomanovsky

    def getMesurmentsArrayRomanovskyBeta(self):
        return self.__mesurmentsArrayRomanovskyBeta

    def __init__(self, measurementsNumber, measurementsArray, inf):
        super().__init__(measurementsNumber, measurementsArray, inf)

    def __add__(self, other):
        if isinstance(other, self.__class__):
            if (self.getNumberOfMesurments() + other.getNumberOfMesurments() <= 20):
                return mesurmentsResultRomanovsky(self.getNumberOfMesurments() + other.getNumberOfMesurments(),
                                                  self.getMesurmentsArrayEnter() + other.getMesurmentsArrayEnter(),
                                                  self.getInf())
            else:
                print("Воспользуйтесь критерием Шарли")
        else:
            raise TypeError("Не могу добавить {1} к {0}".
                            format(self.__class__, type(other)))

    def __subtract__(self, other):
        if isinstance(other, self.__class__):
            if (self.getNumberOfMesurments() - other.getNumberOfMesurments() > 1):
                if (self.getNumberOfMesurments() - other.getNumberOfMesurments() <= 20):
                    return mesurmentsResultRomanovsky(self.getNumberOfMesurments() - other.getNumberOfMesurments(),
                                                      self.getMesurmentsArrayEnter() - other.getMesurmentsArrayEnter(),
                                                      self.getInf())
                else:
                    print("Воспользуйтесь критерием Шарли")
            else:
                print("Не могу вычесть {1} из {0}".format(self.getNumberOfMesurments(), other.getNumberOfMesurments()))
        else:
            TypeError("Не могу вычесть {1} из {0}".
                      format(self.__class__, type(other)))

    def set_criterionRomanovsky(self, a):
        numberOfMesurments = super().getNumberOfMesurments()

        #print(
        #    "Type '1', if you want significance level to be 0.01. Type '2', if you want significance level to be 0.02.")
        #print(
        #   "Type '3', if you want significance level to be 0.05. Type '4', if you want significance level to be 0.10.")
        caseSwitch = a
        criterionRomanovsky = 1

        if caseSwitch == 1:

            if (numberOfMesurments == 4):
                criterionRomanovsky = 1.73
            elif (4 < numberOfMesurments and numberOfMesurments < 6):
                criterionRomanovsky = (numberOfMesurments - 4) * (2.16 - 1.73) / (6 - 4)
            elif (numberOfMesurments == 6):
                criterionRomanovsky = 2.16
            elif (6 < numberOfMesurments and numberOfMesurments < 8):
                criterionRomanovsky = (numberOfMesurments - 6) * (2.43 - 2.16) / (8 - 6)
            elif (numberOfMesurments == 8):
                criterionRomanovsky = 2.43  # 5
            elif (8 < numberOfMesurments and numberOfMesurments < 10):
                criterionRomanovsky = (numberOfMesurments - 8) * (2.62 - 2.43) / (10 - 8)
            elif (numberOfMesurments == 10):
                criterionRomanovsky = 2.62
            elif (10 < numberOfMesurments and numberOfMesurments < 12):
                criterionRomanovsky = (numberOfMesurments - 10) * (2.75 - 2.62) / (12 - 10)
            elif (numberOfMesurments == 12):
                criterionRomanovsky = 2.75
            elif (12 < numberOfMesurments and numberOfMesurments < 15):
                criterionRomanovsky = (numberOfMesurments - 12) * (2.90 - 2.75) / (15 - 12)
            elif (numberOfMesurments == 15):
                criterionRomanovsky = 2.90  # 11
            elif (15 < numberOfMesurments and numberOfMesurments < 20):
                criterionRomanovsky = (numberOfMesurments - 15) * (3.08 - 2.90) / (20 - 15)
            else:
                criterionRomanovsky = 3.08


        elif caseSwitch == 2:

            if (numberOfMesurments == 4):
                criterionRomanovsky = 1.72  # 1
            elif (4 < numberOfMesurments and numberOfMesurments < 6):
                criterionRomanovsky = (numberOfMesurments - 4) * (2.13 - 1.72) / (6 - 4)
            elif (numberOfMesurments == 6):
                criterionRomanovsky = 2.13
            elif (6 < numberOfMesurments and numberOfMesurments < 8):
                criterionRomanovsky = (numberOfMesurments - 6) * (2.37 - 2.13) / (8 - 6)
            elif (numberOfMesurments == 8):
                criterionRomanovsky = 2.37
            elif (8 < numberOfMesurments and numberOfMesurments < 10):
                criterionRomanovsky = (numberOfMesurments - 8) * (2.54 - 2.37) / (10 - 8)
            elif (numberOfMesurments == 10):
                criterionRomanovsky = 2.54
            elif (10 < numberOfMesurments and numberOfMesurments < 12):
                criterionRomanovsky = (numberOfMesurments - 10) * (2.66 - 2.54) / (12 - 10)
            elif (numberOfMesurments == 12):
                criterionRomanovsky = 2.66
            elif (12 < numberOfMesurments and numberOfMesurments < 15):
                criterionRomanovsky = (numberOfMesurments - 12) * (2.80 - 2.66) / (15 - 12)
            elif (numberOfMesurments == 15):
                criterionRomanovsky = 2.80
            elif (15 < numberOfMesurments and numberOfMesurments < 20):
                criterionRomanovsky = (numberOfMesurments - 15) * (2.96 - 2.80) / (20 - 15)
            else:
                criterionRomanovsky = 2.96

        elif caseSwitch == 3:

            if (numberOfMesurments == 4):
                criterionRomanovsky = 1.71
            elif (4 < numberOfMesurments and numberOfMesurments < 6):
                criterionRomanovsky = (numberOfMesurments - 4) * (2.10 - 1.71) / (6 - 4)
            elif (numberOfMesurments == 6):
                criterionRomanovsky = 2.10
            elif (6 < numberOfMesurments and numberOfMesurments < 8):
                criterionRomanovsky = (numberOfMesurments - 6) * (2.27 - 2.10) / (8 - 6)
            elif (numberOfMesurments == 8):
                criterionRomanovsky = 2.27
            elif (8 < numberOfMesurments and numberOfMesurments < 10):
                criterionRomanovsky = (numberOfMesurments - 8) * (2.41 - 2.27) / (10 - 8)
            elif (numberOfMesurments == 10):
                criterionRomanovsky = 2.41
            elif (10 < numberOfMesurments and numberOfMesurments < 12):
                criterionRomanovsky = (numberOfMesurments - 10) * (2.52 - 2.41) / (12 - 10)
            elif (numberOfMesurments == 12):
                criterionRomanovsky = 2.52
            elif (12 < numberOfMesurments and numberOfMesurments < 15):
                criterionRomanovsky = (numberOfMesurments - 12) * (2.64 - 2.52) / (15 - 12)
            elif (numberOfMesurments == 15):
                criterionRomanovsky = 2.64
            elif (15 < numberOfMesurments and numberOfMesurments < 20):
                criterionRomanovsky = (numberOfMesurments - 15) * (2.78 - 2.64) / (20 - 15)
            else:
                criterionRomanovsky = 2.78

        elif caseSwitch == 4:

            if numberOfMesurments == 4:
                criterionRomanovsky = 1.69  # 1
            elif 4 < numberOfMesurments and numberOfMesurments < 6:
                criterionRomanovsky = (numberOfMesurments - 4) * (2.00 - 1.69) / (6 - 4)
            elif numberOfMesurments == 6:
                criterionRomanovsky = 2.00
            elif 6 < numberOfMesurments and numberOfMesurments < 8:
                criterionRomanovsky = (numberOfMesurments - 6) * (2.17 - 2.00) / (8 - 6)
            elif numberOfMesurments == 8:
                criterionRomanovsky = 2.17
            elif 8 < numberOfMesurments and numberOfMesurments < 10:
                criterionRomanovsky = (numberOfMesurments - 8) * (2.29 - 2.17) / (10 - 8)
            elif numberOfMesurments == 10:
                criterionRomanovsky = 2.29
            elif 10 < numberOfMesurments and numberOfMesurments < 12:
                criterionRomanovsky = (numberOfMesurments - 10) * (2.39 - 2.29) / (12 - 10)
            elif numberOfMesurments == 12:
                criterionRomanovsky = 2.39
            elif 12 < numberOfMesurments and numberOfMesurments < 15:
                criterionRomanovsky = (numberOfMesurments - 12) * (2.49 - 2.39) / (15 - 12)
            elif numberOfMesurments == 15:
                criterionRomanovsky = 2.49
            elif 15 < numberOfMesurments and numberOfMesurments < 20:
                criterionRomanovsky = (numberOfMesurments - 15) * (2.62 - 2.49) / (20 - 15)
            else:
                criterionRomanovsky = 2.62

        else:
            print("Default case")

        self.__criterionRomanovsky = criterionRomanovsky
        # print("self.criterionRomanovsky",self.criterionRomanovsky)

    def set_mesurmentsArrayRomanovskyBeta(self):
        mesurmentsArrayRomanovskyBeta = []
        mesurmentsArrayDeviations = self.getMesurmentsArrayDeviations()
        # print("mesurmentsArrayDeviations", mesurmentsArrayDeviations )
        for i in mesurmentsArrayDeviations:
            mesurmentsArrayRomanovskyBeta.append(abs(i / self.getStandardDeviation()))
            # print(abs(i / super().getStandardDeviation()))
        # print("бета",mesurmentsArrayRomanovskyBeta)
        self.__mesurmentsArrayRomanovskyBeta = mesurmentsArrayRomanovskyBeta
        # print("self.mesurmentsArrayRomanovskyBeta",self.mesurmentsArrayRomanovskyBeta)

    def set_mesurmentsArrayResult(self):
        mesurmentsArrayResult = []
        numberOfMesurmentsResult = 0
        count = 0
        # print("self.mesurmentsArrayDeviations",self.mesurmentsArrayDeviations)
        # print("self.criterionRomanovsky", self.getCriterionRomanovsky())
        # print("self.getMesurmentsArrayRomanovskyBeta()", self.getMesurmentsArrayRomanovskyBeta())
        MesurmentsArrayRomanovskyBeta = self.getMesurmentsArrayRomanovskyBeta()
        CriterionRomanovsky = self.getCriterionRomanovsky()
        for i in MesurmentsArrayRomanovskyBeta:
            # print("i",i, CriterionRomanovsky)
            if (int(i) > int(CriterionRomanovsky)):
                mesurmentsArrayResult.append(0)
            else:
                mesurmentsArrayResult.append(self.getMesurmentsArrayEnter()[count])
                numberOfMesurmentsResult = numberOfMesurmentsResult + 1
            count = count + 1

        # self.__numberOfMesurmentsResult = numberOfMesurmentsResult
        super().set_numberOfMesurmentsResult(numberOfMesurmentsResult)
        # numberOfMesurmentsResult = numberOfMesurmentsResult
        # self.__mesurmentsArrayResult = mesurmentsArrayResult
        super().set_mesurmentsArrayResult(mesurmentsArrayResult)
        # print(numberOfMesurmentsResult)
        # print(mesurmentsArrayResult)
        # print("self.getNumberOfMesurmentsResult()", self.getNumberOfMesurmentsResult())
        # print("self.getMesurmentsArrayResult()", self.getMesurmentsArrayResult())

    def set_mesurmentsResultResult(self, **kwargs):

        mesurmentsArrayResultSumm = 0

        for i in self.getMesurmentsArrayResult():
            mesurmentsArrayResultSumm = mesurmentsArrayResultSumm + i

        mesurmentsResultResult = mesurmentsArrayResultSumm / self.getNumberOfMesurmentsResult()

        # print("mesurmentsArrayResultSumm", mesurmentsArrayResultSumm)
        # print("self.numberOfMesurmentsResult", self.numberOfMesurmentsResult)

        # self.__mesurmentsResultResult = mesurmentsResultResult
        super().set_mesurmentsResultResult(mesurmentsResultResult)

    def printClass(self):
        numberOfMesurments = super().getNumberOfMesurments()
        print("Results in tabular form")
        print("Program changes Gross errors to 0")
        print()

        print("i".ljust(10), "Array".ljust(20), "Deviations".ljust(20), "DeviationsSquared".ljust(20), "Beta".ljust(20),
              "Result".ljust(20))
        print(
            "---------------------------------------------------------------------------------------------------------------")

        i = 0
        while i < numberOfMesurments:
            print(str(i + 1).ljust(10),
                  str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayRomanovskyBeta()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            print(
                "---------------------------------------------------------------------------------------------------------------")
            i = i + 1

        print("Average enter element= ", self.getMesurmentsArrayEnterAverage())
        print("criterionRomanovsky= ", self.getCriterionRomanovsky())
        print("Average element after gross errors deleted= ", self.getMesurmentsResultResult())


    def printClassToList(self):
        listOfFullInformation = []
        numberOfMesurments = super().getNumberOfMesurments()
        listOfFullInformation.append("Criterion Romanovski")
        listOfFullInformation.append("Results in tabular form")
        listOfFullInformation.append("Program changes Gross errors to 0")
        listOfFullInformation.append("")

        listOfFullInformation.append("i".ljust(10) + "Array".ljust(20) + "Deviations".ljust(20) + "DeviationsSquared".ljust(20) + "Beta".ljust(20) +
              "Result".ljust(20))
        listOfFullInformation.append(
            "---------------------------------------------------------------------------------------------------------------")

        i = 0
        while i < numberOfMesurments:
            listOfFullInformation.append(str(i + 1).ljust(10) +
                  str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20) +
                  str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20) +
                  str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20) +
                  str(round(self.getMesurmentsArrayRomanovskyBeta()[i], 3)).ljust(20) +
                  str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            listOfFullInformation.append(
                "---------------------------------------------------------------------------------------------------------------")
            i = i + 1

        listOfFullInformation.append("Average enter element= " + str(self.getMesurmentsArrayEnterAverage()))
        listOfFullInformation.append("criterionRomanovsky= " + str(self.getCriterionRomanovsky()))
        listOfFullInformation.append("Average element after gross errors deleted= " + str(self.getMesurmentsResultResult()))

        return listOfFullInformation




    def printClassToFile(self, dirfile, namefile):

        # Работа с файлом(запись в файл):

        namefile = dirfile + namefile  # полный путь к файлу
        myfile = open(namefile, 'w')  # Открывает файл (создает/очищает)

        myfile.write("Romanovsky criterion")
        myfile.write('\n')
        myfile.write("Results in tabular form")
        myfile.write('\n')
        myfile.write("Program changes Gross errors to 0")
        myfile.write('\n')
        myfile.write("i".ljust(10) + "Array".ljust(20) + "Deviations".ljust(20) +
                     "DeviationsSquared".ljust(20) + "Beta".ljust(20) + "Result".ljust(20))
        myfile.write('\n')

        myfile.write(
            "---------------------------------------------------------------------------------------------------------------")
        myfile.write('\n')
        i = 0

        while i < self.getNumberOfMesurments():
            myfile.write(str(i + 1).ljust(10) +
                         str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20) +
                         str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20) +
                         str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20) +
                         str(round(self.getMesurmentsArrayRomanovskyBeta()[i], 3)).ljust(20) +
                         str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            myfile.write('\n')
            myfile.write(
                "---------------------------------------------------------------------------------------------------------------")
            myfile.write('\n')
            i = i + 1

        myfile.write('\n')
        myfile.write("Average enter element= " + str(self.getMesurmentsArrayEnterAverage()))
        myfile.write('\n')
        myfile.write("criterionRomanovsky= " + str(self.getCriterionRomanovsky()))
        myfile.write('\n')
        myfile.write("Average element after gross errors deleted= " + str(self.getMesurmentsResultResult()))
        myfile.write('\n')

        myfile.close()

    #@method_friendly_decorator
    def makeAllWork(self, a):
        self.makeBeatifulMesurmentsResult()
        self.set_criterionRomanovsky(a)
        self.set_mesurmentsArrayRomanovskyBeta()
        self.set_mesurmentsArrayResult()
        self.set_mesurmentsResultResult()
        #self.printClass()


#################################################################################################################################################################################

def clicked():
    User = type('User', bases, attrs)

    first_name = txt1.get()
    last_name = txt2.get()
    middle_name = txt3.get()

    user1 = User(first_name, last_name, middle_name)
    lbl1.configure(text=user1.get_first_name())
    lbl2.configure(text=user1.get_last_name())
    lbl3.configure(text=user1.get_middle_name())
    return user1

def clicked2():
    measurementsNumber=int(txt4.get())
    lbl4.configure(text=measurementsNumber)
    #lbl5.configure(text=0)

    return measurementsNumber

def clicked3():
    if(len(measurementsArray)<int(lbl4.cget("text"))):

        measurementsArray.append(float(txt5.get()))
        txt5.configure(text="")
        lbl5_0.configure(text=measurementsArray)
    else:
        lbl5.configure(text="Приступайте к расчетам!")

    return y

def clicked4():
    if(len(measurementsArray)==int(lbl4.cget("text"))):
        lbl6.configure(text="Приступаем к разработке!")
        inf = lbl1.cget("text") + " " + lbl2.cget("text") + " " + lbl3.cget("text")
        if (len(measurementsArray) <= 20):
            mesurmentsArrayClass01 = mesurmentsResultRomanovsky(len(measurementsArray), measurementsArray, inf)
            a = int(selected.get())
            mesurmentsArrayClass01.makeAllWork(a)
            #txt6.configure(mesurmentsArrayClass01.printClassToList())
            lst = mesurmentsArrayClass01.printClassToList()
            t = txt6
            for x in lst:
                t.insert(END, x + '\n')

        else:
            mesurmentsArrayClass01 = mesurmentsResultCharlier(len(measurementsArray), measurementsArray, inf)
            mesurmentsArrayClass01.makeAllWork()
            #txt6.configure(mesurmentsArrayClass01.printClassToList())
            lst = mesurmentsArrayClass01.printClassToList()
            t = txt6
            for x in lst:
                t.insert(END, x + '\n')

    else:
        lbl6.configure(text="Добавьте результатов вычисления!")
    return y




window = Tk()

measurementsArray = []
y = 0

window.geometry('1500x600')
window.title("Оценка ряда измерений")

lbl1 = Label(window, text="Введите имя:", font=("Arial Bold", 10))
lbl1.grid(column=0, row=0)

txt1 = Entry(window, width=10)
txt1.grid(column=1, row=0)

lbl2 = Label(window, text="Введите фамилию:", font=("Arial Bold", 10))
lbl2.grid(column=0, row=1)

txt2 = Entry(window, width=10)
txt2.grid(column=1, row=1)

lbl3 = Label(window, text="Введите отчество:", font=("Arial Bold", 10))
lbl3.grid(column=0, row=2)

txt3 = Entry(window, width=10)
txt3.grid(column=1, row=2)

btn = Button(window, text="Отправить ФИО", command=clicked)
btn.grid(row=3, columnspan=2)

lbl4 = Label(window, text="Количество измерений:", font=("Arial Bold", 10))
lbl4.grid(column=0, row=4)

txt4 = Entry(window, width=10)
txt4.grid(column=1, row=4)

btn2 = Button(window, text="Установить количество измерений", command=clicked2)
btn2.grid(row=5, columnspan=2)

lbl5_0 = Label(window, text="Здесь будет список значений", font=("Arial Bold", 10))
lbl5_0.grid(row=15, columnspan=4)

lbl5 = Label(window, text="Введите результат измерения:", font=("Arial Bold", 10))
lbl5.grid(column=0, row=7)

txt5 = Entry(window, width=10)
txt5.grid(column=1, row=7)

btn3 = Button(window, text="Загрузить измерение", command=clicked3)
btn3.grid(row=8, columnspan=2)

lbl6 = Label(window, text="", font=("Arial Bold", 10))
lbl6.grid(row=9, columnspan=2)

lbl7 = Label(window, text="Уровень значимости(выбиретмя только для количества измерений<20)", font=("Arial Bold", 10))
lbl7.grid(row=10, columnspan=2)

selected = IntVar()
rad1 = Radiobutton(window, text='0.01', value=1, variable=selected)
rad2 = Radiobutton(window, text='0.02', value=2, variable=selected)
rad3 = Radiobutton(window, text='0.05', value=3, variable=selected)
rad4 = Radiobutton(window, text='0.10', value=4, variable=selected)
rad1.grid(column=0, row=11)
rad2.grid(column=1, row=11)
rad3.grid(column=2, row=11)
rad4.grid(column=3, row=11)

btn4 = Button(window, text="Приступить к расчетам", command=clicked4)
btn4.grid(row=12, columnspan=3)

txt6 = scrolledtext.ScrolledText(window, width=125, height=15)
txt6.grid(column=3, row=0, rowspan=10)

lbl8 = Label(window, text="Информация о программе:", font=("Arial Bold", 10))
lbl8.grid(column=0, row=16)
###########################################################################################
lbl8 = Label(window, text="Замысел зачетного задания состоит в следующем: когда мы измеряем некую природную величину (например мы измеряем силу источника света, размер готовой детали, расход воды в водопроводе) то многократно измеряя одну и ту же", font=("Arial Bold", 10))
lbl8.grid(column=0, row=17, columnspan=4)

lbl8 = Label(window, text="величину(многократные измерения нужны для повышения точности) одним и тем же прибором будут получаться немного разные результаты (пусть и отличающиеся на доли процента). Тем не менее в наш ряд измерений могут закрасться", font=("Arial Bold", 10))
lbl8.grid(column=0, row=18, columnspan=4)

lbl8 = Label(window, text="неверные результаты, получившиеся от ошибок исследователя/или чего-то другого. Для выбраковки из ряда измерений неверной величины(ошибки) применяются различные коэффициенты. В своей работе я реализовал работу коэффициентов", font=("Arial Bold", 10))
lbl8.grid(column=0, row=19, columnspan=4)

lbl8 = Label(window, text="Шарли(более 20 измерений) и  Романовского(менее 20 измерений). Ход работы: исследователь получает ряд измерений, с помощью метрологических коэффициентов из ряда измерений исключаются грубые ошибки (если такие присутствуют), ", font=("Arial Bold", 10))
lbl8.grid(column=0, row=20, columnspan=4)

lbl8 = Label(window, text="из ряда измерений с выброшенными ошибками мы находим среднее арифметическое, которое и будет являться максимально приближенным к истине значением измеряемой величины.", font=("Arial Bold", 10))
lbl8.grid(column=0, row=21, columnspan=4)

lbl8 = Label(window, text="Ход работы:", font=("Arial Bold", 10))
lbl8.grid(column=0, row=22)

lbl8 = Label(window, text="1. Пользователь вводит имя, фамилию и отчество. Нажимает кнопку 'Отправить ФИО' ", font=("Arial Bold", 10))
lbl8.grid(column=0, row=23, columnspan=4)

lbl8 = Label(window, text="2. Пользователь вводит количество измерений. Нажимает кнопку 'Установить количество измерений' ", font=("Arial Bold", 10))
lbl8.grid(column=0, row=24, columnspan=4)

lbl8 = Label(window, text="3. Пользователь по очереди вводит результаты измерений. Нажимает кнопку 'Загрузить измерение' ", font=("Arial Bold", 10))
lbl8.grid(column=0, row=25, columnspan=4)

lbl8 = Label(window, text="4. Пользователь устанавливает критерий значимости(если количество измерений не превышает 20).", font=("Arial Bold", 10))
lbl8.grid(column=0, row=26, columnspan=4)

lbl8 = Label(window, text="5. Пользователь нажимает кнопку 'приступить к расчетам'", font=("Arial Bold", 10))
lbl8.grid(column=0, row=27, columnspan=4)

############################################################################################


window.mainloop()















# Печать вступительной информации:
print("Metrology. Gross errors.")
print()
print("A gross error, or a miss, is the error of the result of an individual measurement")
print("included in a series of measurements, which for these conditions differs sharply")
print("from the rest of the results of this series.For single measurements, detect a miss")
print("impossible.With multiple measurements to detect misses use statistical criterion:")
print("Romanovsky, Charlier.")
print()
print("This program demonstrates work of two criterias: Romanovsky criteria, Charlier criteria.")
print()
print("Romanovsky criterion is applied if the number of measurements is less than 20.")
print("Charlier criterion is applied if the number of measurements is more than 20.")
print()



