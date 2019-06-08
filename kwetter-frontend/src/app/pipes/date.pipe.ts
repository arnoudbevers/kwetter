import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

/**
 * Transforms a given unix timestamp (in seconds) into a human readable date using the moment.js library.
 * Takes a format that defaults to the default format (ISO 8601)
 * 
 * Usage: 
 *  value | date:format
 *
 * Example: 
 *  {{ 1559559215 | date }}
 *  formats to 2019-06-03T10:53:35+00:00
 * 
 * Example: 
 *  {{ 1559559215 | date:'MM-DD-YYYY' }}
 *  formats to 06-03-2019
 */
@Pipe({
  name: 'date'
})
export class DatePipe implements PipeTransform {

  transform(value: number, format?: string): string | moment.Moment {
    return (format == undefined) ? moment.unix(value) : moment.unix(value).format(format);
  }

}
