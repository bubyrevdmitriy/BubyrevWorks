import {Pipe, PipeTransform} from "@angular/core";

@Pipe({name: 'setterDateOnlyHHMMPipe'})
export class SetterDateOnlyHHMMPipe implements PipeTransform{
  transform(dateBeforeTransform: Date): any {
    let array = JSON.parse("[" +dateBeforeTransform + "]");

    let hours: string = array[3].toString();
    let minutes: string = array[4].toString();
    if (hours.length<2) {
      hours = '0' + hours;
    }
    if (minutes.length<2) {
      minutes = '0' + minutes;
    }
    return hours+":"+minutes;
  }
}
