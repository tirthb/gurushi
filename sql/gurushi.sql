select * from scripture;
select * from chapter;
select * from verse_line;
select * from author;
select * from commentary;

select 
c.title chapter_title, 
c.number chapter_number, 
v.number_range verse_number,
vl.line,
t.text,
co.text
from verse v
inner join chapter c on v.chapter_id = c.id
inner join verse_line vl on vl.verse_id = v.id
inner join translation t on t.verse_id = v.id
inner join commentary co on co.verse_id = v.id and co.text = ''
order by c.sort_order, v.sort_order, vl.sort_order;

