import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MemberService } from 'src/app/services/member.service';
import { MemberReq } from 'src/app/types/MemberReq';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html',
  styleUrls: ['./add-member.component.css']
})
export class AddMemberComponent implements OnInit {
  ngOnInit(): void {
  }
  constructor(private memberService : MemberService){}
  text : string = 'Member'
  yourEntityFields = [
    { type: 'text', label: 'Name', name: 'name', inputType: 'text'},
    { type: 'text', label: 'Family Name', name: 'familyName', inputType: 'text' },
    { type: 'text', label: 'Nationality', name: 'nationality', inputType: 'text' },
    { name: 'identityNumber', label: 'Identity number', type: 'text', inputType: 'text'},
    {
      label: 'Identity type',
      type: 'select',
      name: 'identityDocument',
      options: [
        { label: 'CIN', value: 'CIN' },
        { label: 'Passport', value: 'PASSPORT' },
        { label: 'Carte Résidence', value: 'CARTE_RESIDENCE' }
      ]
    },
  ];
  formData:any = {};
  @Output() add = new EventEmitter();
  onFormSubmit(formData: any): void {
    if (formData) {
      const newMember: MemberReq = {
        name : formData.name,
        familyName : formData.familyName,
        nationality : formData.nationality,
        identityDocument : formData.identityDocument,
        identityNumber : formData.identityNumber,
      };

      if (newMember) {
        this.add.emit(newMember);
      }

      formData.name = '',
      formData.familyName = '',
      formData.nationality = '',
      formData.identityDocument = '',
      formData.identityNumber = ''   }
  }

}
