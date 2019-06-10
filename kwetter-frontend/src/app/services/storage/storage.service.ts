import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class StorageService {
  constructor() {}

  /**
   * Gets an item from the local storage. 
   * @param itemKey Key to get the item by.
   * @returns String representation of the value corresponding to given key.
   */
  public getItem(itemKey: string) {
    return localStorage.getItem(itemKey);
  }

  /**
   * Adds or changes an item in the local storage.
   * Adds - when itemKey does not exist yet.
   * Changes - when itemKey already exists.
   * @param itemKey Key of the item to set.
   * @param itemValue (New) value of the item to set.
   */
  public setItem(itemKey: string, itemValue: any) {
    localStorage.setItem(itemKey, itemValue);
  }

  /**
   * Removes an item from the local storage.
   * @param itemKey Key of the item to remove.
   */
  public removeItem(itemKey: string) {
    localStorage.removeItem(itemKey);
  }
}
