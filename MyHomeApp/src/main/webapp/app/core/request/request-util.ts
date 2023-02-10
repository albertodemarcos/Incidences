import { HttpParams } from '@angular/common/http';

export const createRequestOption = (req?: any): HttpParams => {
  let options: HttpParams = new HttpParams();

  if (req) {
    Object.keys(req).forEach(key => {
      console.log('key' + key);
      if ( (key !== 'sort' && key !== 'filter' ) && req[key]) {
        for (const value of [].concat(req[key]).filter(v => v !== '')) {
          options = options.append(key, value);
        }
      }
    });

    if (req.sort) {
      req.sort.forEach((val: string) => {
        options = options.append('sort', val);
      });
    }

    if (req.filter) {
      req.filter.forEach((value: string, key: string) => {
        console.log(`key: ${key} , value: ${value}`);
        options = options.append(key, value);
      });
    }
  }

  return options;
};
