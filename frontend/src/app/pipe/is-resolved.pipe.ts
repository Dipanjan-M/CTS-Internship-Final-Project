import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'isResolved'
})
export class IsResolvedPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return value == null ? "Not resolved yet" : value;
  }

}
