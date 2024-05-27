from pydantic import BaseModel, ConfigDict
from datetime import datetime


class Listing(BaseModel):    
    model_config = ConfigDict(from_attributes=True)

    title: str
    description: str
    price: int
    user_id: int
    image_url: str
    pub_date: datetime