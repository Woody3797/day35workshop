import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Observable, of } from "rxjs";
import { Book } from "./Book";

const URL = 'http://localhost:8080/api/books/search'

@Injectable()
export class BookService {

    http = inject(HttpClient)

    getBooksByTitle(title: string): Observable<Book[]> {
        const params = new HttpParams().set('title', title)
        if (title.length == 0) {
            return of<Book[]>([])
        }

        return this.http.get<Book[]>(URL, {params: params})
    }
}