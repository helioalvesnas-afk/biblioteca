import { ApiResponse } from "../dto/api-response";

export class GenericMapper<T> {
  static mapApiResponseToData<T>(response: ApiResponse<T>): T {
    return response.dado;
  }
}
