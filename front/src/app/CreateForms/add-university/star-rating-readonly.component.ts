import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-star-rating-readonly',
  template: `
    <span *ngFor="let star of stars; let i = index"
          [ngClass]="{'filled': i < roundedRating}">
      ★
    </span>
    <small>({{ rating | number:'1.1-1' }}/5)</small>
  `,
  styles: [`
    span {
      font-size: 1.5rem;
      color: lightgray;
    }
    .filled {
      color: gold;
    }
  `]
})
export class StarRatingReadonlyComponent {
  @Input() rating = 0; // average rating (e.g. 3.7)
  stars = [1, 2, 3, 4, 5];

  get roundedRating() {
    return Math.round(this.rating); // display nearest whole star
  }
}