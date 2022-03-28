import {Pipe, PipeTransform} from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({name: 'safeHtml'})
export class SafeHtml implements PipeTransform{
  constructor(private sanitizer: DomSanitizer) {}

  /*transform(value: number, args?: any): string {
    return value.toString().replace(".", ",");
  }*/

  transform(html) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(html);
  }
}
