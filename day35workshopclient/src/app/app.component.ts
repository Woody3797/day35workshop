import { Component, OnInit, inject } from '@angular/core';
import { Observable, Subject, debounceTime, filter, map, mergeMap, tap } from 'rxjs';
import { Book } from './Book';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from './BookService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    books$!: Observable<Book[]>
    form$!: FormGroup
    bookService = inject(BookService)
    fb = inject(FormBuilder)
    titleInput = new Subject<string>

    ngOnInit(): void {
        this.form$ = this.fb.group({
            title: this.fb.control('', [Validators.required])
        })
        
        this.books$ = this.titleInput.pipe(
            filter(title => title.trim().length >= 0),
            debounceTime(1000),
            mergeMap(title => this.bookService.getBooksByTitle(title)), // map returns observable of observable<Book>, mergeMap returns the Observable as it is
        )
    }

    getBooksByTitle(title: string) {
        this.books$ = this.bookService.getBooksByTitle(title)
    }

    keypressed(title: string) {
        console.info(title)
        this.titleInput.next(title)
    }

}
