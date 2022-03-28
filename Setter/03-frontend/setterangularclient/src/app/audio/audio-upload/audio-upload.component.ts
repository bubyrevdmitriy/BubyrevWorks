import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AudioService} from "../../service/audio.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
// @ts-ignore
import { MatProgressSpinnerModule } from '@angular/material';
import {Post} from "../../models/post";
import {NewStreamFile} from "../../models/new-stream-file";
@Component({
  selector: 'app-audio-upload',
  templateUrl: './audio-upload.component.html',
  styleUrls: ['./audio-upload.component.css']
})
export class AudioUploadComponent implements OnInit {

  currentPageHostCategory: string;
  currentPageId: number;

  isError: boolean = false;
  isFileSelected: boolean = false;
  isLoadingProcess: boolean = false;


  //new try
  selectedFile: File;
  audioUploadForm: FormGroup;
  newStreamFile: NewStreamFile;


  @Output()
  uploadRequest = new EventEmitter<string>();

  constructor(private route: ActivatedRoute,
              private audioService: AudioService,
              private fb: FormBuilder) { }

  ngOnInit(): void {
    this.currentPageHostCategory = this.route.snapshot.paramMap.get('hostCategory');
    this.currentPageId = +this.route.snapshot.paramMap.get('id');
    this.audioUploadForm = this.createAudioUploadForm();
  }

  private createAudioUploadForm() {
    return this.fb.group({
      description: ['', Validators.compose([Validators.required])],
      file: ['', Validators.compose([Validators.required])]
    });
  }

  get formValue(){
    return this.audioUploadForm.controls;
  }

  onFileChange(event:any) {
    if (event.target.files && event.target.files[0]) {
      this.selectedFile = event.target.files[0];
      this.audioUploadForm.value.file = event.target.files[0];
    }
  }


  submitNewAudio() {
    let newStreamFile = new NewStreamFile();
    newStreamFile.description = this.audioUploadForm.value.description;
    //newStreamFile.file = this.selectedFile;

    if (this.currentPageHostCategory == 'groups') {
      newStreamFile.groupId = String(this.currentPageId);
    }


    this.isLoadingProcess = true;
    this.audioService.uploadNewAudio(newStreamFile, this.selectedFile)
      .subscribe((data) => {
        this.isError = false;
        this.isLoadingProcess = false;
        //window.location.reload()
        this.audioUploadForm.reset();
        this.selectedFile  = null;
        this.uploadRequest.emit('upload');
      }, error => {
        this.isError = true;
      });
  }

  /*
  submit(): void {
    this.authService.login({
      email: this.loginFormGroup.value.email,
      password: this.loginFormGroup.value.password
    }).subscribe(data => {
      this.status = '!Already loaded!';
      this.tokenStorage.saveToken(data.token);
      this.tokenStorage.saveUser(data);

      //this.notificationService.showSnackBar('Successfully logged in');
      this.router.navigate(['/']);

      window.location.reload();
    }, error => {
      this.status = 'Incorrect email and password!';
      this.isError = true;
      //this.notificationService.showSnackBar(error.message);
    })
  }*/
}
