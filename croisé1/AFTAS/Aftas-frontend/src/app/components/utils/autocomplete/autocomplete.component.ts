import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MemberService } from 'src/app/services/member.service';
import { Member } from 'src/app/types/member';

@Component({
  selector: 'app-autocomplete',
  templateUrl: './autocomplete.component.html',
  styleUrls: ['./autocomplete.component.css'],
})
export class AutocompleteComponent implements OnInit {
  members: Member[] = [];
  filteredMembers: Member[] = [];
  member: string | undefined = undefined;
  selectedMember : Member | undefined = undefined;
  @Output() formSubmit: EventEmitter<any> = new EventEmitter<any>();

  constructor(private memberService: MemberService) {}

  ngOnInit(): void {
    this.memberService.getMembers().subscribe((members) => {
      this.members = members;
      this.filteredMembers = this.members;
    });
  }

  filterMembers(event: Event): void {
    const query = (event.target as HTMLInputElement).value.toLowerCase();
    this.filteredMembers = this.members.filter((m) => m.name.includes(query));
  }

  selectMember(m: Member): void {
    this.selectedMember = m;
    this.member = `${m.num} - ${m.name} ${m.familyName}`;
  }
  submitForm() {
    console.log("Submit");
    console.log(this.selectedMember)
    this.formSubmit.emit(this.selectedMember);
    this.selectedMember = undefined;
  }
}
