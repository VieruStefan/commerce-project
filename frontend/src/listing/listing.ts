export class Listing {
    id: number;
    title: string;
    description: string;
    price: number;
    user_id: number;
    image_url: string;
    updated_date: string;
    pub_date: string;
  
    constructor(
      id: number,
      title: string,
      description: string,
      price: number,
      user_id: number,
      image_url: string,
      updated_date: string,
      pub_date: string
    ) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.price = price;
      this.user_id = user_id;
      this.image_url = image_url;
      this.updated_date = updated_date;
      this.pub_date = pub_date;
    }
  }
  