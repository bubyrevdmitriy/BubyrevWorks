import { Injectable } from '@angular/core';
import { BrowserXhr } from '@angular/http';
import {TokenStorageService} from "../service/token-storage.service";

@Injectable()
export class CustomBrowserXhr extends BrowserXhr {

  constructor(private tokenService: TokenStorageService) {super();}
  build(): any {
    let xhr = super.build();
    xhr.withCredentials = true;
    return <any>(xhr);
  }
}
