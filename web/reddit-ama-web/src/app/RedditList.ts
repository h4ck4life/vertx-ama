export interface RedditList {
    total: number;
    data:  Datum[];
}

export interface Datum {
    amaId?:     string;
    qaId?:      string;
    title?:     string;
    url?:       string;
    body?:      string;
    question?:  string;
    answer?:    string;
    timestamp?: string;
    error?:     null;
}