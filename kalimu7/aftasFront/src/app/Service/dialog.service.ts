import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogContentComponent } from '../Component/dialog-content/dialog-content.component';
@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(private dialog:MatDialog) { }

  openDialog():void{
    this.dialog.open(DialogContentComponent,{
      width :'600px',
      maxHeight:'90vh'
    })
  }

}
