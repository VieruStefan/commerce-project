export class Listing {
    id: number;
    title: string;
    description: string;
    seller: number;
    image_url: string;
    pub_date: string;
  
    constructor(
      id: number,
      title: string,
      description: string,
      seller: number,
      image_url: string,
      pub_date: string
    ) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.seller = seller;
      this.image_url = image_url;
      this.pub_date = pub_date;
    }
  }
  