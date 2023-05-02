
  export interface IIncidenceKanbanDTO {
    id?: number;
    title?: string | null;
    startDate?: Date | null;
    status?: string | null;
    priority?: string | null;
    visible?: boolean | false;
  }
  
  export class IncidenceKanbanDTO implements IIncidenceKanbanDTO {
    constructor(
      public id?: number,
      public title?: string| null,
      public startDate?: Date| null,
      public status?: string| null,
      public priority?: string | null,
      public visible?: boolean | false
    ) {}

  }