import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Observable, map } from "rxjs";
import { Book } from "./Book";

interface ApiResponse {
    bookID: number
    title: string
    authors: string
    average_rating: number
}

const URL = 'http://localhost:8080/api/books/search'

@Injectable()
export class BookService {

    http = inject(HttpClient)

    getBooksByTitle(title: string): Observable<Book[]> {
        const params = new HttpParams().set('title', title)

        return this.http.get<Book[]>(URL, {params: params})
    }
}