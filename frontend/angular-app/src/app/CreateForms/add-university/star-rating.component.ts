import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-star-rating',
  template: `
    <span *ngFor="let star of stars; let i = index"
          (click)="rate(i + 1)"
          [ngClass]="{'filled': i < rating}">
      ★
    </span>
  `,
  styles: [`
    span {
      font-size: 1.5rem;
      cursor: pointer;
      color: gray;
    }
    .filled {
      color: gold;
    }
  `]
})
export class StarRatingComponent {
  @Input() rating = 0;  // average or user rating
  @Output() ratingClick = new EventEmitter<number>();

  stars = [1, 2, 3, 4, 5];

  rate(value: number) {
    this.ratingClick.emit(value);
  }
}