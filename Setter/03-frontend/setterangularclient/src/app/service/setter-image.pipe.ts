import {Pipe, PipeTransform} from "@angular/core";

@Pipe({name: 'setterImagePipe'})
export class SetterImagePipe implements PipeTransform {
  transform(img: any): any {
    if (img == null) {
      return null;
    }
    return 'data:image/jpeg;base64,' + img;
  }
}
