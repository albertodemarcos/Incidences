export interface Pagination {
  page: number;
  size: number;
  sort: string[];
  filter: Map<string, string>;
}

export interface Search {
  query: string;
}

export interface SearchWithPagination extends Search, Pagination {}
