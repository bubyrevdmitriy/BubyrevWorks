import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AudioService} from "../../service/audio.service";
import {ActivatedRoute, Router} from "@angular/router";
import {VideoService} from "../../service/video.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NewStreamFile} from "../../models/new-stream-file";
// @ts-ignore
import { MatProgressSpinnerModule } from '@angular/material';

@Component({
  selector: 'app-video-upload',
  templateUrl: './video-upload.component.html',
  styleUrls: ['./video-upload.component.css']
})
export class VideoUploadComponent implements OnInit {

  currentPageHostCategory: string;
  currentPageId: number;

  isError: boolean = false;
  isFileSelected: boolean = false;
  //public videoUploadForm: FormGroup;
  isLoadingProcess: boolean = false;

  //new try
  selectedFile: File;
  videoUploadForm: FormGroup;
  newStreamFile: NewStreamFile;

  selectedPreview: File;
  previewImgURL: any;
  isPreviewImgURLLoaded = false;

  @Output()
  uploadRequest = new EventEmitter<string>();

  constructor(private route: ActivatedRoute,
              private videoService: VideoService,
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit(): void {
    this.currentPageHostCategory = this.route.snapshot.paramMap.get('hostCategory');
    this.currentPageId = +this.route.snapshot.paramMap.get('id');
    this.videoUploadForm = this.createVideoUploadForm();
  }

  private createVideoUploadForm() {
    return this.fb.group({
      description: ['', Validators.compose([Validators.required])],
      file: ['', Validators.compose([Validators.required])]
    });
  }

  get formValue(){
    return this.videoUploadForm.controls;
  }

  onFileChange(event:any) {
    if (event.target.files && event.target.files[0]) {
      this.selectedFile = event.target.files[0];
      this.videoUploadForm.value.file = event.target.files[0];
    }
  }


  submitNewVideo() {
    let newStreamFile = new NewStreamFile();
    newStreamFile.description = this.videoUploadForm.value.description;
    //newStreamFile.file = this.selectedFile;

    if (this.currentPageHostCategory == 'groups') {
      newStreamFile.groupId = String(this.currentPageId);
    }

    this.isLoadingProcess = true;

    console.log(this.selectedPreview);

    this.videoService.uploadNewVideo(newStreamFile, this.selectedFile, this.selectedPreview)
      .subscribe((data) => {
        this.isError = false;
        this.isLoadingProcess = false;
        this.videoUploadForm.reset();
        this.selectedFile  = null;

        this.selectedPreview = null;
        this.previewImgURL = null;
        this.isPreviewImgURLLoaded = false;

        this.uploadRequest.emit('upload');
      }, error => {
        this.isError = true;
      });
  }

  onFileSelected(event) {
    this.selectedPreview = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.selectedPreview);
    reader.onload = () => {
      this.previewImgURL = reader.result;
      this.isPreviewImgURLLoaded = true;
    };
  }

}
